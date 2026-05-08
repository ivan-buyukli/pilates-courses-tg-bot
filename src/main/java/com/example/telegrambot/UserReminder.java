package com.example.telegrambot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class UserReminder {

    @Id
    private Long chatId;

    private Integer sentCount;

    private Instant nextSendAt;

    private Long lastUpdateId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public Instant getNextSendAt() {
        return nextSendAt;
    }

    public void setNextSendAt(Instant nextSendAt) {
        this.nextSendAt = nextSendAt;
    }

    public Long getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(Long lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }
}