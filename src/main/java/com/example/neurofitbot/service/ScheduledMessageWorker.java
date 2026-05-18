package com.example.neurofitbot.service;

import com.example.neurofitbot.bot.NeurofitTelegramBot;
import com.example.neurofitbot.common.MessageStatus;
import com.example.neurofitbot.message.ScheduledMessage;
import com.example.neurofitbot.message.ScheduledMessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledMessageWorker {

    private final ScheduledMessageRepository scheduledMessageRepository;
    private final MessageSenderService messageSenderService;
    private final NeurofitTelegramBot bot;

    public ScheduledMessageWorker(
            ScheduledMessageRepository scheduledMessageRepository,
            MessageSenderService messageSenderService,
            NeurofitTelegramBot bot
    ) {
        this.scheduledMessageRepository = scheduledMessageRepository;
        this.messageSenderService = messageSenderService;
        this.bot = bot;
    }

    @Scheduled(fixedDelay = 1_000)
    public void sendPendingMessages() {
        List<ScheduledMessage> messages =
                scheduledMessageRepository
                        .findTop50ByStatusAndScheduledAtLessThanEqualOrderByScheduledAtAsc(
                                MessageStatus.PENDING,
                                LocalDateTime.now()
                        );

        for (ScheduledMessage scheduledMessage : messages) {
            try {
                Long chatId = scheduledMessage.getUser().getTelegramUserId();

                messageSenderService.sendPreparedMessage(
                        bot,
                        chatId,
                        scheduledMessage.getPreparedMessage()
                );

                scheduledMessage.markSent();
            } catch (Exception e) {
                scheduledMessage.markFailed(e.getMessage());
            }

            scheduledMessageRepository.save(scheduledMessage);
        }
    }
}
