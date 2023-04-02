FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR qr-generator-example
COPY --from=build target/*.jar qr-generator-example.jar
ENTRYPOINT ["java", "-jar", "qr-generator-example.jar"]