FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

COPY org.ekstazi.core-5.3.0.jar org.ekstazi.core-5.3.0.jar
ADD https://github.com/ufoscout//docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait

CMD /wait && java -javaagent:org.ekstazi.core-5.3.0.jar=mode=MULTI -jar /app.jar

# Before adding the ekstazi jar
# ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
# RUN chmod +x /wait

# CMD /wait && java -jar /app.jar