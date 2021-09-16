rm -rf build
rm -rf .gradle
export SPRING_PROFILES_ACTIVE=local
./scripts/wait-for-it.sh localstack:4566
wait 15
export USER_POOL_ID=`sed 's/"//g' user_pool_id.txt`
./gradlew bootRun
