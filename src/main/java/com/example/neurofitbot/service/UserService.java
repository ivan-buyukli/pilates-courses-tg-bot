package com.example.neurofitbot.service;

import com.example.neurofitbot.user.BotUser;
import com.example.neurofitbot.user.BotUserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final BotUserRepository userRepository;

    public UserService(BotUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BotUser findOrCreate(User telegramUser) {
        return userRepository.findByTelegramUserId(telegramUser.getId())
                .map(existing -> {
                    existing.setLastActivityAt(LocalDateTime.now());
                    return userRepository.save(existing);
                })
                .orElseGet(() -> {
                    BotUser user = new BotUser();
                    user.setTelegramUserId(telegramUser.getId());
                    user.setUsername(telegramUser.getUserName());
                    user.setFirstName(telegramUser.getFirstName());
                    user.setLastActivityAt(LocalDateTime.now());
                    return userRepository.save(user);
                });
    }
}
