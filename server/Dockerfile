FROM eclipse-temurin:17-jdk-alpine AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ./mvnw -B -nsu dependency:resolve dependency:resolve-plugins

COPY src src
RUN ./mvnw clean package


FROM quay.io/wildfly/wildfly:latest-jdk17
WORKDIR server
COPY --from=build target/web3-1.0.0.war /opt/jboss/wildfly/standalone/deployments/ROOT.war

EXPOSE 8080