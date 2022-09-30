FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY --chown=777 . ./
RUN ./gradlew clean build -x test

ENTRYPOINT java -jar build/libs/rabitmqpoc-*-SNAPSHOT.jar
