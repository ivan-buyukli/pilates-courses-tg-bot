package com.example.neurofitbot;

import com.example.neurofitbot.bot.NeuroFitBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({NeuroFitBotProperties.class})
public class NeurofitBotApplication {

    static void main(String[] args) {
        SpringApplication.run(NeurofitBotApplication.class, args);
    }

}
