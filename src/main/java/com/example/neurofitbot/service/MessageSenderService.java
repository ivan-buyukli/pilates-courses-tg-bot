package com.example.neurofitbot.service;

import com.example.neurofitbot.common.ButtonType;
import com.example.neurofitbot.common.MediaType;
import com.example.neurofitbot.common.MessageCode;
import com.example.neurofitbot.message.PreparedMessage;
import com.example.neurofitbot.user.BotUser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static com.example.neurofitbot.common.ReplyButtonConstants.CONTACT_ME;
import static com.example.neurofitbot.common.ReplyButtonConstants.JOIN_THE_COURSE;

@Service
public class MessageSenderService {

    public void sendPreparedMessage(TelegramLongPollingBot bot, Long chatId, PreparedMessage message) throws Exception {

        if (message.getTextBefore() != null && !message.getTextBefore().isBlank()) {
            if (MessageCode.NF_WELCOME_INTRO == message.getCode()) {
                bot.execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(message.getTextBefore())
                        .parseMode("HTML")
                        .replyMarkup(buildHotKeyboardButtons())
                        .build());
            } else {
                bot.execute(SendMessage.builder()
                        .chatId(chatId)
                        .text(message.getTextBefore())
                        .parseMode("HTML")
                        .replyMarkup(buildButton(message))
                        .build());
            }
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

    public void onContactMeButton(TelegramLongPollingBot bot, Long chatId) throws Exception {
        bot.execute(SendMessage.builder()
                .chatId(chatId)
                .text("Є питання? Напиши мені в дірект!")
                .parseMode("HTML")
                .replyMarkup(buildURLButton("Написати", "https://t.me/@JuliButenko"))
                .build());
    }

    public void onJoinTheCourseButton(TelegramLongPollingBot bot, Long chatId) throws Exception {
        bot.execute(SendMessage.builder()
                .chatId(chatId)
                .text("Дізнайся детальніше про курс за посиланням нижче!")
                .parseMode("HTML")
                .replyMarkup(buildURLButton("Дізнатися про курс", "https://butenkofit.com/neurofit"))
                .build());
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

    private InlineKeyboardMarkup buildURLButton(String buttonText, String buttonValue) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonText);
        button.setUrl(buttonValue);

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(List.of(button)))
                .build();
    }

    private ReplyKeyboardMarkup buildHotKeyboardButtons() {
        KeyboardRow row = new KeyboardRow();
        row.add(CONTACT_ME);
        row.add(JOIN_THE_COURSE);

        return ReplyKeyboardMarkup.builder()
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .keyboard(List.of(row))
                .build();

    }
}
