./gradlew build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t satishsarraf/portfolio-service .
#docker-compose up
