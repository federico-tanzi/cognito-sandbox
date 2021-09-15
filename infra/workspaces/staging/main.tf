locals {
  stage = "staging"
  region = "us-east-1"
}

provider "aws" {
  region  = local.region
}

module "user_pool" {
  source = "../../modules/user_pool"
  stage = local.stage
}


terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
  }
  backend "remote" {
    hostname = "app.terraform.io"
    organization = "masgroup"

    workspaces {
      name = "cognito-develop"
    }
  }
}
