FROM openjdk:20-slim

COPY ./build/libs/LogManager-1.0-SNAPSHOT.jar /app/PILogManager.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/PILogManager.jar"]