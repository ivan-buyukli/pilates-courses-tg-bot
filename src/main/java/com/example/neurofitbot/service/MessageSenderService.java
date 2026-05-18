package com.example.neurofitbot.service;

import com.example.neurofitbot.common.ButtonType;
import com.example.neurofitbot.common.MediaType;
import com.example.neurofitbot.message.PreparedMessage;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
public class MessageSenderService {

    public void sendPreparedMessage(TelegramLongPollingBot bot, Long chatId, PreparedMessage message) throws Exception {

        if (message.getTextBefore() != null && !message.getTextBefore().isBlank()) {
            bot.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(message.getTextBefore())
                    .parseMode("HTML")
                    .replyMarkup(buildButton(message))
                    .build());
        }

        if (message.getMediaType() != MediaType.NONE) {
            sendMedia(bot, chatId, message);
        }

        if (message.getTextAfter() != null && !message.getTextAfter().isBlank()) {
            bot.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(message.getTextAfter())
                    .parseMode("HTML")
                    .replyMarkup(buildButton(message))
                    .build());
        }
    }

    private void sendMedia(TelegramLongPollingBot bot, Long chatId, PreparedMessage message) throws Exception {

        InputFile file = new InputFile(message.getTelegramFileId());

        switch (message.getMediaType()) {
            case PHOTO -> bot.execute(SendPhoto.builder()
                    .chatId(chatId)
                    .photo(file)
                    .caption(message.getCaption())
                    .replyMarkup(buildButton(message))
                    .build());

            case VIDEO -> bot.execute(SendVideo.builder()
                    .chatId(chatId)
                    .video(file)
                    .caption(message.getCaption())
                    .replyMarkup(buildButton(message))
                    .build());

            case VOICE -> bot.execute(SendVoice.builder()
                    .chatId(chatId)
                    .voice(file)
                    .caption(message.getCaption())
                    .replyMarkup(buildButton(message))
                    .build());

            default -> {
            }
        }
    }

    private InlineKeyboardMarkup buildButton(PreparedMessage message) {
        if (message.getButtonText() == null || message.getButtonText().isBlank()) {
            return null;
        }

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(message.getButtonText());

        if (ButtonType.URL == message.getButtonType()) {
            button.setUrl(message.getButtonValue());
        } else {
            button.setCallbackData(message.getButtonValue());
        }

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(List.of(button)))
                .build();
    }
}
