./gradlew build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t satishsarraf/quotes-service .
docker push satishsarraf/quotes-service
#kubectl delete -f carts.yaml
#kubectl create -f carts.yaml
