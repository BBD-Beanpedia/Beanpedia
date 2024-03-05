#!/usr/bin/env node
import "source-map-support/register";
import * as cdk from "aws-cdk-lib";
import { BeanStack } from "../lib/bean-stack";

const app = new cdk.App();
new BeanStack(app, "BeanStack", {
  tags: {
    owner: "jacques.mouton@bbd.co.za",
    "created-using": "cdk",
  },
  env: { account: "958933916692", region: "eu-west-1" },
  repositoryConfig: [{ owner: "BBD-Beanpedia", repo: "Beanpedia" }],
});
