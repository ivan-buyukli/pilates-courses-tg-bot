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

# Copy the application jar
COPY --from=build /app/target/neurofit-bot-0.0.1.jar app.jar

# Copy wait-for-postgres script and make it executable
# (the script will be copied as root then we switch ownership to appuser if necessary)
COPY --chown=appuser:appuser scripts/wait-for-postgres.sh /app/wait-for-postgres.sh
RUN chmod +x /app/wait-for-postgres.sh || true

# Use the wait-for-postgres script as the entrypoint to ensure DB is reachable
ENTRYPOINT ["/app/wait-for-postgres.sh", "java", "-jar", "app.jar"]
