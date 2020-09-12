./gradlew build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t satishsarraf/accounts-service .
docker push satishsarraf/accounts-service
#kubectl delete -f carts.yaml
#kubectl create -f carts.yaml
