./gradlew build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t satishsarraf/portfolio-service .
docker push satishsarraf/portfolio-service
#kubectl delete -f carts.yaml
#kubectl create -f carts.yaml
