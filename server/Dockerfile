FROM eclipse-temurin:17-jdk-alpine AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn

COPY src src
RUN ./mvnw clean package

FROM eclipse-temurin:17-jre-alpine

RUN mkdir -p /server
WORKDIR server

COPY --from=build target/*.jar web3-1.0.0.jar

ENTRYPOINT ["java", "-jar", "web3-1.0.0.jar"]
EXPOSE 8080