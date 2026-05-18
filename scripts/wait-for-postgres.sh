#!/usr/bin/env bash
set -e

# wait-for-postgres.sh
# Wait for a Postgres server to be available before launching the application.
# Behavior:
# - Reads SPRING_DATASOURCE_URL (jdbc:postgresql://host:port/db) to find host and port
# - Falls back to DB_HOST=postgres and DB_PORT=5432 if parsing fails
# - Tries to open a TCP socket to host:port using bash /dev/tcp
# - After success sleeps 1s to allow Postgres to be ready, then execs the app jar

SPRING_URL=${SPRING_DATASOURCE_URL:-}
DB_HOST=${DB_HOST:-}
DB_PORT=${DB_PORT:-}

# Try to parse host and port from SPRING_DATASOURCE_URL if DB_HOST/DB_PORT not provided
if [ -z "$DB_HOST" ] && [ -n "$SPRING_URL" ]; then
  # extract between jdbc:postgresql:// and next / or end
  DB_HOST=$(echo "$SPRING_URL" | sed -E 's#jdbc:postgresql://([^:/]+).*#\1#')
fi

if [ -z "$DB_PORT" ] && [ -n "$SPRING_URL" ]; then
  DB_PORT=$(echo "$SPRING_URL" | sed -E 's#jdbc:postgresql://[^:/]+:([0-9]+).*#\1#')
fi

# defaults
DB_HOST=${DB_HOST:-postgres}
DB_PORT=${DB_PORT:-5432}

MAX_RETRIES=${WAIT_FOR_POSTGRES_MAX_RETRIES:-60}
SLEEP_SECONDS=${WAIT_FOR_POSTGRES_SLEEP_SECONDS:-1}

echo "[wait-for-postgres] Waiting for Postgres at $DB_HOST:$DB_PORT (max retries: $MAX_RETRIES)"

count=0
while true; do
  if (exec 3<>/dev/tcp/$DB_HOST/$DB_PORT) >/dev/null 2>&1; then
    echo "[wait-for-postgres] Postgres is reachable at $DB_HOST:$DB_PORT"
    break
  fi

  count=$((count+1))
  if [ $count -ge $MAX_RETRIES ]; then
    echo "[wait-for-postgres] Timeout after $MAX_RETRIES attempts waiting for $DB_HOST:$DB_PORT"
    exit 1
  fi
  sleep $SLEEP_SECONDS
done

# give Postgres a tiny moment to finish startup
sleep 1

# Exec the command (if provided) or default to running the jar
if [ $# -gt 0 ]; then
  echo "[wait-for-postgres] Executing: $@"
  exec "$@"
else
  echo "[wait-for-postgres] Executing: java $JAVA_OPTS -jar app.jar"
  exec java $JAVA_OPTS -jar app.jar
fi

