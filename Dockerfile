FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre
# Create a non-root user and switch to it for security
RUN useradd -ms /bin/bash appuser
USER appuser
WORKDIR /app

COPY --from=build /app/target/telegram-bot-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]