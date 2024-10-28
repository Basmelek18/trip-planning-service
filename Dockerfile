
FROM openjdk:22-jdk-slim

WORKDIR /app

COPY target/trip-planning-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080