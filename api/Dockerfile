FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /api

COPY pom.xml .

RUN mvn validate

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /api

COPY --from=build /api/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]