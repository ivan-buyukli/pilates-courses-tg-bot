package com.example.neurofitbot.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bot_users")
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long telegramUserId;

    private String username;

    private String firstName;

    private boolean isActive = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime lastActivityAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(Long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(LocalDateTime lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }
}
