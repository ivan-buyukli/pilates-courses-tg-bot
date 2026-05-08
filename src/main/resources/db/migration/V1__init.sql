-- Example Flyway migration: create user_reminder table
CREATE TABLE IF NOT EXISTS user_reminder (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    reminder_text VARCHAR(255) NOT NULL,
    remind_at TIMESTAMP NOT NULL
);

