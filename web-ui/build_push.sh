./gradlew build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t satishsarraf/web-ui-service .
docker push satishsarraf/web-ui-service
#kubectl delete -f carts.yaml
#kubectl create -f carts.yaml
