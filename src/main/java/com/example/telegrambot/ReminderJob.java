package com.example.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ReminderJob {

    private final TelegramClient telegramClient;
    private final UserReminderRepository repository;
    private final String helloMessage;
    private final Integer maxReminders;

    public ReminderJob(
            TelegramClient telegramClient,
            UserReminderRepository repository,
            @Value("${telegram.hello-message}") String helloMessage,
            @Value("${telegram.max-reminders}") Integer maxReminders
    ) {
        this.telegramClient = telegramClient;
        this.repository = repository;
        this.helloMessage = helloMessage;
        this.maxReminders = maxReminders;
    }

    @Scheduled(fixedDelay = 60000)
    public void sendScheduledMessages() {
        List<UserReminder> reminders =
                repository.findByNextSendAtLessThanEqualAndSentCountLessThan(
                        Instant.now(),
                        maxReminders
                );

        for (UserReminder reminder : reminders) {
            telegramClient.sendMessageWithMainKeyboard(reminder.getChatId(), helloMessage);;

            int newCount = reminder.getSentCount() + 1;
            reminder.setSentCount(newCount);

            if (newCount >= maxReminders) {
                reminder.setNextSendAt(null);
            } else {
                reminder.setNextSendAt(Instant.now().plus(24, ChronoUnit.HOURS));
            }

            repository.save(reminder);
        }
    }
}