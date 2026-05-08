package com.example.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Component
public class TelegramPollingJob {

    private final TelegramClient telegramClient;
    private final UserReminderRepository repository;
    private final String triggerMessage;
    private final String helloMessage;

    private Long lastProcessedUpdateId;

    public TelegramPollingJob(
            TelegramClient telegramClient,
            UserReminderRepository repository,
            @Value("${telegram.trigger-message}") String triggerMessage,
            @Value("${telegram.hello-message}") String helloMessage
    ) {
        this.telegramClient = telegramClient;
        this.repository = repository;
        this.triggerMessage = triggerMessage;
        this.helloMessage = helloMessage;
    }

    @Scheduled(fixedDelay = 5000)
    public void pollMessages() {
        Long offset = lastProcessedUpdateId == null ? null : lastProcessedUpdateId + 1;
        Map response = telegramClient.getUpdates(offset);

        List<Map<String, Object>> updates = (List<Map<String, Object>>) response.get("result");

        for (Map<String, Object> update : updates) {
            Long updateId = ((Number) update.get("update_id")).longValue();
            lastProcessedUpdateId = updateId;

            Map<String, Object> message = (Map<String, Object>) update.get("message");
            if (message == null) {
                continue;
            }

            String text = (String) message.get("text");
            if (!triggerMessage.equalsIgnoreCase(text)) {
                continue;
            }

            Map<String, Object> chat = (Map<String, Object>) message.get("chat");
            Long chatId = ((Number) chat.get("id")).longValue();

            telegramClient.sendMessage(chatId, helloMessage);

            UserReminder reminder = repository.findById(chatId).orElseGet(UserReminder::new);
            reminder.setChatId(chatId);
            reminder.setSentCount(0);
            reminder.setNextSendAt(Instant.now().plus(24, ChronoUnit.HOURS));
            reminder.setLastUpdateId(updateId);

            repository.save(reminder);
        }
    }
}