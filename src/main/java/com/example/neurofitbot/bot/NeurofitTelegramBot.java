package com.example.neurofitbot.bot;

import com.example.neurofitbot.service.MessageSenderService;
import com.example.neurofitbot.service.ScenarioService;
import com.example.neurofitbot.service.UserService;
import com.example.neurofitbot.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.neurofitbot.common.ReplyButtonConstants.CONTACT_ME;
import static com.example.neurofitbot.common.ReplyButtonConstants.JOIN_THE_COURSE;

@Component
public class NeurofitTelegramBot extends TelegramLongPollingBot {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NeurofitTelegramBot.class);

    private final NeuroFitBotProperties properties;
    private final UserService userService;
    private final ScenarioService scenarioService;
    private final MessageSenderService messageSenderService;

    public NeurofitTelegramBot(
            NeuroFitBotProperties properties,
            UserService userService,
            ScenarioService scenarioService,
            MessageSenderService messageSenderService
    ) {
        if (properties == null || properties.getToken() == null || properties.getToken().isBlank()) {
            throw new IllegalStateException("Telegram bot token is not configured. Please set TELEGRAM_BOT_TOKEN / telegram.bot-neurofit.token");
        }
        if (properties.getUsername() == null || properties.getUsername().isBlank()) {
            throw new IllegalStateException("Telegram bot username is not configured. Please set TELEGRAM_BOT_USERNAME / telegram.bot-neurofit.username");
        }

        super(properties.getToken());
        this.properties = properties;
        this.userService = userService;
        this.scenarioService = scenarioService;
        this.messageSenderService = messageSenderService;
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMessage(update);
        }

        if (update.hasCallbackQuery()) {
            handleCallback(update);
        }
    }

    private void handleMessage(Update update) {
        String text = update.getMessage().getText();

        BotUser user = userService.findOrCreate(update.getMessage().getFrom());

        if ("/start".equals(text)) {
            scenarioService.startScenario(user);
        }
        try {
            if (CONTACT_ME.equals(text)) {
                messageSenderService.onContactMeButton(this, update.getMessage().getChatId());
            }
        } catch (Exception e) {
            log.error("Error while sending contact me message to user {}: {}", user.getTelegramUserId(), e.getMessage());
        }
        try {
            if (JOIN_THE_COURSE.equals(text)) {
                messageSenderService.onJoinTheCourseButton(this, update.getMessage().getChatId());
            }
        } catch (Exception e) {
            log.error("Error while sending join the course message to user {}: {}", user.getTelegramUserId(), e.getMessage());
        }
    }

    private void handleCallback(Update update) {
        String callbackData = update.getCallbackQuery().getData();

        BotUser user = userService.findOrCreate(update.getCallbackQuery().getFrom());

        if ("ВІДЕО КООРДИНАЦІЯ".equals(callbackData)) {
            scenarioService.onCoordinationVideoButton(user);
        }
        if ("ВІДЕО ДЛЯ ОЧЕЙ".equals(callbackData)) {
            scenarioService.onVideoForEyesButton(user);
        }
        if ("ВІДЕО ДИХАННЯ".equals(callbackData)) {
            scenarioService.onVideoForBreathingButton(user);
        }
        if ("ВІДЕО З МЯЧАМИ".equals(callbackData)) {
            scenarioService.onVideoExercisesWithBallButton(user);
        }
    }
}
