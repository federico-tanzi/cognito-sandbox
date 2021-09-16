resource "aws_ssm_parameter" "poolId" {
  name  = "/cognito/userPool/id/${var.stage}"
  type  = "String"
  value = aws_cognito_user_pool.pool.id
}

resource "aws_ssm_parameter" "clientId" {
  name  = "/cognito/userPool/client/id/${var.stage}"
  type  = "String"
  value = aws_cognito_user_pool_client.client.id
}

output "poolId" {
  value = aws_cognito_user_pool.pool.id
}
