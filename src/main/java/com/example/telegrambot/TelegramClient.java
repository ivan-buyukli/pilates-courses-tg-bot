package com.example.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class TelegramClient {

    private final RestClient restClient;

    public TelegramClient(@Value("${telegram.bot-token}") String token) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.telegram.org/bot" + token)
                .build();
    }

    public Map getUpdates(Long offset) {
        String uri = offset == null
                ? "/getUpdates?timeout=20"
                : "/getUpdates?timeout=20&offset=" + offset;

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(Map.class);
    }

    public void sendMessage(Long chatId, String text) {
        restClient.post()
                .uri("/sendMessage")
                .body(Map.of(
                        "chat_id", chatId,
                        "text", text
                ))
                .retrieve()
                .toBodilessEntity();
    }

    public void sendMessageWithMainKeyboard(Long chatId, String text) {
        restClient.post()
                .uri("/sendMessage")
                .body(Map.of(
                        "chat_id", chatId,
                        "text", text,
                        "reply_markup", Map.of(
                                "keyboard", List.of(
                                        List.of("\uD83E\uDDD8 Courses", "\uD83D\uDCC5 Schedule"),
                                        List.of("✅ Book a class", "\uD83D\uDCB3 Prices"),
                                        List.of("\uD83D\uDCCD Location", "\uD83D\uDCDE Contact"),
                                        List.of("❓ Help")
                                ),
                                "resize_keyboard", true
                        )
                ))
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteMessage(Long chatId, Long messageId) {
        restClient.post()
                .uri("/deleteMessage")
                .body(Map.of(
                        "chat_id", chatId,
                        "message_id", messageId
                ))
                .retrieve()
                .toBodilessEntity();
    }
}