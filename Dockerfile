FROM maven:3.9.9-eclipse-temurin-21-alpine as maven_cache
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B &&\
    mvn dependency:resolve-plugins -B &&\
    mvn dependency:resolve -B

FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY --from=maven_cache /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:21-jre-alpine-3.22
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]