package com.example.neurofitbot.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BotUserRepository extends JpaRepository<BotUser, Long> {
    Optional<BotUser> findByTelegramUserId(Long telegramUserId);
}
