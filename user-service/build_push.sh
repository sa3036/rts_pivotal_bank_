./gradlew build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t satishsarraf/users-service .
docker push satishsarraf/users-service
#kubectl delete -f carts.yaml
#kubectl create -f carts.yaml
