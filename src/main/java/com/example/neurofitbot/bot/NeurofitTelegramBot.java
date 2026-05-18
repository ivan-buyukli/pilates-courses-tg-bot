package com.example.neurofitbot.bot;

import com.example.neurofitbot.service.ScenarioService;
import com.example.neurofitbot.service.UserService;
import com.example.neurofitbot.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class NeurofitTelegramBot extends TelegramLongPollingBot {

    private final NeuroFitBotProperties properties;
    private final UserService userService;
    private final ScenarioService scenarioService;

    public NeurofitTelegramBot(
            NeuroFitBotProperties properties,
            UserService userService,
            ScenarioService scenarioService
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
    }

    private void handleCallback(Update update) {
        String callbackData = update.getCallbackQuery().getData();

        BotUser user = userService.findOrCreate(update.getCallbackQuery().getFrom());

        if ("ВІДЕО КООРДИНАЦІЯ".equals(callbackData)) {
            scenarioService.onBonusReceived(user);
        }
        if ("ВІДЕО ДЛЯ ОЧЕЙ".equals(callbackData)) {
            scenarioService.onVideoForEyesReceived(user);
        }
        if ("ВІДЕО ДИХАННЯ".equals(callbackData)) {
            scenarioService.onVideoForBreathingReceived(user);
        }
        if ("ВІДЕО З МЯЧАМИ".equals(callbackData)) {
            scenarioService.onVideoExercisesWithBallReceived(user);
        }
    }
}
