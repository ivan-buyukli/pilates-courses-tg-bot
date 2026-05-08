package com.example.telegrambot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface UserReminderRepository extends JpaRepository<UserReminder, Long> {
    List<UserReminder> findByNextSendAtLessThanEqualAndSentCountLessThan(
            Instant now,
            Integer maxReminders
    );
}