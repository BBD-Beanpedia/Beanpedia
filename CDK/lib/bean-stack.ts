import * as cdk from "aws-cdk-lib";
import { aws_ec2 as ec2, aws_iam as iam, aws_rds as rds } from "aws-cdk-lib";
import { Construct } from "constructs";
import { GitHubStackProps } from "./github-stack-props";
import { Effect, PolicyDocument, PolicyStatement } from "aws-cdk-lib/aws-iam";

export class BeanStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: GitHubStackProps) {
    super(scope, id, props);

    const vpc = new ec2.Vpc(this, "BeanVpc", {
      maxAzs: 2,
      subnetConfiguration: [
        {
          name: "PublicSubnet",
          subnetType: ec2.SubnetType.PUBLIC,
        },
      ],
    });

    const beanEc2SG = new ec2.SecurityGroup(this, "BeanEc2SG", {
      vpc,
      allowAllOutbound: false,
    });

    const beanDbSG = new ec2.SecurityGroup(this, "BeanDbSG", {
      vpc,
      allowAllOutbound: false,
    });

    // beanDbSG.addIngressRule(ec2.Peer.anyIpv4(), ec2.Port.tcp(5432));
    // beanDbSG.addEgressRule(ec2.Peer.anyIpv4(), ec2.Port.tcp(5432));

    const beanAPI = new ec2.BastionHostLinux(this, "BeanAPI", {
      vpc,
      securityGroup: beanEc2SG,
      instanceType: ec2.InstanceType.of(
        ec2.InstanceClass.T3,
        ec2.InstanceSize.MICRO
      ),
      subnetSelection: {
        subnetType: ec2.SubnetType.PUBLIC,
      },
      blockDevices: [
        {
          deviceName: "/dev/sdh",
          volume: ec2.BlockDeviceVolume.ebs(20, {
            encrypted: true,
          }),
        },
      ],
    });

    // const dbInstance = new rds.DatabaseInstance(this, "BeanDbInstance", {
    //   engine: rds.DatabaseInstanceEngine.postgres({
    //     version: rds.PostgresEngineVersion.VER_16,
    //   }),
    //   instanceType: ec2.InstanceType.of(
    //     ec2.InstanceClass.T3,
    //     ec2.InstanceSize.MICRO
    //   ),
    //   vpc,
    //   vpcSubnets: {
    //     subnetType: ec2.SubnetType.PUBLIC,
    //   },
    //   allocatedStorage: 20,
    //   publiclyAccessible: true,
    //   deletionProtection: false,
    //   credentials: rds.Credentials.fromGeneratedSecret("admin", {
    //     secretName: "BeanDbSecret",
    //   }),
    //   securityGroups: [beanDbSG],
    // });
    // dbInstance.connections.allowFrom(beanAPI, ec2.Port.tcp(5432));

    // // Create role for github actions to assume
    // const githubDomain = "token.actions.githubusercontent.com";

    // const ghProvider = new iam.OpenIdConnectProvider(this, "githubProvider", {
    //   url: `https://${githubDomain}`,
    //   clientIds: ["sts.amazonaws.com"],
    // });

    // const iamRepoDeployAccess = props?.repositoryConfig.map(
    //   (r) => `repo:${r.owner}/${r.repo}:${r.filter ?? "*"}`
    // );

    // const conditions: iam.Conditions = {
    //   StringLike: {
    //     [`${githubDomain}:sub`]: iamRepoDeployAccess,
    //   },
    // };

    // new iam.Role(this, "gitHubDeployRole", {
    //   assumedBy: new iam.WebIdentityPrincipal(
    //     ghProvider.openIdConnectProviderArn,
    //     conditions
    //   ),
    //   inlinePolicies: {
    //     allowAssumeCDKRoles: new PolicyDocument({
    //       statements: [
    //         new PolicyStatement({
    //           actions: ["sts:AssumeRole"],
    //           effect: Effect.ALLOW,
    //           resources: ["arn:aws:iam::*:role/cdk-*"],
    //         }),
    //         new PolicyStatement({
    //           actions: ["secretsmanager:GetSecretValue"],
    //           effect: Effect.ALLOW,
    //           resources: ["*"],
    //         }),
    //       ],
    //     }),
    //   },
    //   roleName: "BeanDeployRole",
    //   description:
    //     "This role is used via GitHub Actions to deploy with AWS CDK on the target AWS account",
    //   maxSessionDuration: cdk.Duration.hours(1),
    // });
  }
}
