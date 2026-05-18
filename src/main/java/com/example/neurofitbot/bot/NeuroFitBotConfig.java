package com.example.neurofitbot.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class NeuroFitBotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(NeurofitTelegramBot bot) throws Exception {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
}
