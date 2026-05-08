# Production Deployment Guide for telegram-bot

## Prerequisites
- Docker and Docker Compose installed
- PostgreSQL database (or use the provided service)
- Environment variables for secrets and credentials

## Environment Variables
Create a `.env` file (do not commit real secrets):

```
TELEGRAM_BOT_TOKEN=your-telegram-bot-token
SPRING_DATASOURCE_URL=your-db-url
SPRING_DATASOURCE_USERNAME=your-db-username
SPRING_DATASOURCE_PASSWORD=your-db-pass
POSTGRES_USER=your-db-username
POSTGRES_PASSWORD=your-db-pass
```

## Build and Run

```
docker compose up --build -d
```

## Health Checks
- Health endpoint: `http://localhost:8080/actuator/health`
- Metrics endpoint: `http://localhost:8080/actuator/metrics`

## Logs
- Application logs are written to `logs/telegram-bot.log` (with rotation)

## Database Migrations
- Flyway runs automatically on startup. Place migration scripts in `src/main/resources/db/migration/`.

## Security
- All secrets are externalized via environment variables.
- The Docker image runs as a non-root user.

## Resource Limits
- JVM and container resource limits are set in `docker-compose.yaml`.

## Updating
- Rebuild the image and restart containers after code or config changes.

---
For further customization, see the official Spring Boot and Docker documentation.

