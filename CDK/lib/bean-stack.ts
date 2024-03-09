import * as cdk from "aws-cdk-lib";
import {
  SecretValue,
  aws_ec2 as ec2,
  aws_iam as iam,
  aws_rds as rds,
} from "aws-cdk-lib";
import { Construct } from "constructs";
import { GitHubStackProps } from "./github-stack-props";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";

export class BeanStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: GitHubStackProps) {
    super(scope, id, props);

    const secretNames = ["BeanDbSecret", "BeanApiSecret"];

    const vpc = new ec2.Vpc(this, "BeanVpc", {
      maxAzs: 2,
      subnetConfiguration: [
        {
          name: "PublicSubnet",
          subnetType: ec2.SubnetType.PUBLIC,
        },
        {
          name: "PrivateSubnet",
          subnetType: ec2.SubnetType.PRIVATE_ISOLATED,
        },
      ],
    });

    const beanApiSG = new ec2.SecurityGroup(this, "BeanApiSG", {
      vpc,
      allowAllOutbound: true,
    });

    const beanDbSG = new ec2.SecurityGroup(this, "BeanDbSG", {
      vpc,
      allowAllOutbound: false,
    });

    beanApiSG.addIngressRule(ec2.Peer.anyIpv4(), ec2.Port.tcp(22));
    beanApiSG.addIngressRule(ec2.Peer.anyIpv4(), ec2.Port.tcp(8080));

    const cfnKeyPair = new ec2.CfnKeyPair(this, "BeanKey", {
      keyName: "BeanKey",
    });

    const userData = ec2.UserData.forLinux();

    const nuke = `#!/bin/bash
    pkill -f 'java -jar'
    cd ~/API
    rm -f *.jar
    rm -f *.log`;

    userData.addCommands(
      `wget -O- https://repo.liquibase.com/liquibase.asc | gpg --dearmor > liquibase-keyring.gpg && \
      cat liquibase-keyring.gpg | sudo tee /usr/share/keyrings/liquibase-keyring.gpg > /dev/null && \
      echo 'deb [arch=amd64 signed-by=/usr/share/keyrings/liquibase-keyring.gpg] https://repo.liquibase.com stable main' | sudo tee /etc/apt/sources.list.d/liquibase.list`,
      "apt-get update -y",
      "apt install -y openjdk-21-jdk",
      "apt-get install liquibase"
    );

    userData.addCommands(
      `echo "${nuke}" > /home/ubuntu/nuke.sh`,
      "chmod +x /home/ubuntu/nuke.sh"
    );

    const beanAPI = new ec2.Instance(this, "BeanAPI", {
      vpc: vpc,
      vpcSubnets: {
        subnetType: ec2.SubnetType.PUBLIC,
      },
      securityGroup: beanApiSG,
      instanceType: ec2.InstanceType.of(
        ec2.InstanceClass.T3,
        ec2.InstanceSize.MICRO
      ),
      machineImage: new ec2.GenericLinuxImage({
        "eu-west-1": "ami-0d940f23d527c3ab1",
      }),
      blockDevices: [
        {
          deviceName: "/dev/xvda",
          volume: ec2.BlockDeviceVolume.ebs(20),
        },
      ],
      keyName: cfnKeyPair.keyName,
      userData: userData,
    });

    const beanApiSecret = new secretsmanager.Secret(this, "BeanApiSecret", {
      description: "Stores the public IP and key name of the BeanAPI instance",
      secretObjectValue: {
        instanceIP: SecretValue.unsafePlainText(beanAPI.instancePublicIp),
        keyID: SecretValue.unsafePlainText(
          `/ec2/keypair/${cfnKeyPair.attrKeyPairId}`
        ),
      },
      secretName: secretNames[1],
    });

    const dbInstance = new rds.DatabaseInstance(this, "BeanDbInstance", {
      engine: rds.DatabaseInstanceEngine.postgres({
        version: rds.PostgresEngineVersion.VER_16,
      }),
      instanceType: ec2.InstanceType.of(
        ec2.InstanceClass.T3,
        ec2.InstanceSize.MICRO
      ),
      vpc,
      vpcSubnets: {
        subnetType: ec2.SubnetType.PRIVATE_ISOLATED,
      },
      allocatedStorage: 20,
      publiclyAccessible: false,
      deletionProtection: false,
      credentials: rds.Credentials.fromGeneratedSecret("bean", {
        secretName: secretNames[0],
      }),
      securityGroups: [beanDbSG],
    });
    dbInstance.connections.allowFrom(beanAPI, ec2.Port.tcp(5432));

    // Create role for github actions to assume
    const githubDomain = "token.actions.githubusercontent.com";

    const ghProvider = new iam.OpenIdConnectProvider(this, "githubProvider", {
      url: `https://${githubDomain}`,
      clientIds: ["sts.amazonaws.com"],
    });

    const iamRepoDeployAccess = props?.repositoryConfig.map(
      (r) => `repo:${r.owner}/${r.repo}:${r.filter ?? "*"}`
    );

    const conditions: iam.Conditions = {
      StringLike: {
        [`${githubDomain}:sub`]: iamRepoDeployAccess,
      },
    };

    const beanDeployRole = new iam.Role(this, "BeanDeployRole", {
      assumedBy: new iam.WebIdentityPrincipal(
        ghProvider.openIdConnectProviderArn,
        conditions
      ),
      description: "Role assumed by GitHub Actions",
      roleName: "BeanDeployRole",
      maxSessionDuration: cdk.Duration.hours(1),
    });

    const secretManagerPolicy = new iam.PolicyStatement({
      actions: ["secretsmanager:GetSecretValue"],
      resources: secretNames.map(
        (name) =>
          `arn:aws:secretsmanager:${this.region}:${this.account}:secret:${name}-*`
      ),
      effect: iam.Effect.ALLOW,
    });

    const assumeCDKRolePolicy = new iam.PolicyStatement({
      actions: ["sts:AssumeRole"],
      effect: iam.Effect.ALLOW,
      resources: [`arn:aws:iam::*:role/cdk-*`],
    });

    const parameterAccessPolicy = new iam.PolicyStatement({
      actions: ["ssm:GetParameter"],
      effect: iam.Effect.ALLOW,
      resources: [
        `arn:aws:ssm:${this.region}:${this.account}:parameter/ec2/keypair/${cfnKeyPair.attrKeyPairId}`,
      ],
    });

    beanDeployRole.addToPolicy(secretManagerPolicy);
    beanDeployRole.addToPolicy(assumeCDKRolePolicy);
    beanDeployRole.addToPolicy(parameterAccessPolicy);
  }
}
