apk add --no-cache bash
./wait-for-it.sh "localhost:4566"
terraform init
terraform plan
terraform apply --auto-approve
