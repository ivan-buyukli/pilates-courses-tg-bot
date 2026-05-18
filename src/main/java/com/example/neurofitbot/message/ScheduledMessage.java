package com.example.neurofitbot.message;

import com.example.neurofitbot.common.MessageStatus;
import com.example.neurofitbot.user.BotUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_messages")
public class ScheduledMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private BotUser user;

    @ManyToOne(optional = false)
    private PreparedMessage preparedMessage;

    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.PENDING;

    private LocalDateTime scheduledAt;

    private LocalDateTime sentAt;

    @Column(columnDefinition = "text")
    private String errorMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BotUser getUser() {
        return user;
    }

    public void setUser(BotUser user) {
        this.user = user;
    }

    public PreparedMessage getPreparedMessage() {
        return preparedMessage;
    }

    public void setPreparedMessage(PreparedMessage preparedMessage) {
        this.preparedMessage = preparedMessage;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void markSent() {
        this.status = MessageStatus.SENT;
        this.sentAt = LocalDateTime.now();
    }

    public void markFailed(String errorMessage) {
        this.status = MessageStatus.FAILED;
        this.errorMessage = errorMessage;
    }
}
