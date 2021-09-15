resource "aws_cognito_user_pool" "pool" {
  name = "masgroup-users-${var.stage}"
  schema {
    attribute_data_type = "String"
    name = "roleId"
    mutable = true
    required = false
    string_attribute_constraints {   # if it is a string
      min_length = 0
      max_length = 64
    }
  }
  schema {
    attribute_data_type = "String"
    name = "companyId"
    mutable = true
    required = false
    string_attribute_constraints {   # if it is a string
      min_length = 0
      max_length = 64
    }
  }
}

resource "aws_cognito_user_pool_client" "client" {
  name = "masgroup-cognito-api-client-${var. stage}"
  user_pool_id = aws_cognito_user_pool.pool.id
  generate_secret = false
  explicit_auth_flows = ["ALLOW_ADMIN_USER_PASSWORD_AUTH", "ALLOW_REFRESH_TOKEN_AUTH", "ALLOW_USER_SRP_AUTH"]
  read_attributes = ["custom:roleId","custom:companyId"]
  write_attributes = ["custom:roleId","custom:companyId"]
}