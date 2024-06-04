FROM openjdk:21-slim
ARG JAR_FILE=target/*.jar
COPY ./target/Spring-Boot-Demo-Project-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]