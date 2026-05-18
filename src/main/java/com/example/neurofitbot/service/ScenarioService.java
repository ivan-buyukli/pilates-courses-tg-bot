package com.example.neurofitbot.service;

import com.example.neurofitbot.common.MessageCode;
import com.example.neurofitbot.message.PreparedMessage;
import com.example.neurofitbot.message.PreparedMessageRepository;
import com.example.neurofitbot.message.ScheduledMessage;
import com.example.neurofitbot.message.ScheduledMessageRepository;
import com.example.neurofitbot.user.BotUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScenarioService {

    private final PreparedMessageRepository preparedMessageRepository;
    private final ScheduledMessageRepository scheduledMessageRepository;

    public ScenarioService(
            PreparedMessageRepository preparedMessageRepository,
            ScheduledMessageRepository scheduledMessageRepository
    ) {
        this.preparedMessageRepository = preparedMessageRepository;
        this.scheduledMessageRepository = scheduledMessageRepository;
    }

    public void startScenario(BotUser user) {
        var startScenarioTime = LocalDateTime.now();
        schedule(user, MessageCode.NF_WELCOME_INTRO, startScenarioTime);
        schedule(user, MessageCode.NF_GIFT_NEURO_PROPOSAL, startScenarioTime.plusSeconds(1));
    }

    public void onBonusReceived(BotUser user) {
        var localDateTime = LocalDateTime.now();
        schedule(user, MessageCode.NF_GIFT_NEURO, localDateTime);
        schedule(user, MessageCode.NF_STAY_IN_BOT, localDateTime.plusMinutes(3));
        schedule(user, MessageCode.NF_WHAT_IS_NEUROFIT, localDateTime.plusDays(1).withHour(9).withMinute(0));
    }

    public void onVideoForEyesReceived(BotUser user) {
        var localDateTime = LocalDateTime.now();
        schedule(user, MessageCode.NF_WHAT_IS_NEUROFIT_VIDEO, localDateTime);
        if (localDateTime.getHour() < 15) {
            schedule(user, MessageCode.NF_NERVOUS_SYSTEM_RELOAD, localDateTime.withHour(15).withMinute(0));
        } else {
            schedule(user, MessageCode.NF_NERVOUS_SYSTEM_RELOAD, localDateTime.plusDays(1).withHour(9).withMinute(0));
        }
    }

    public void onVideoForBreathingReceived(BotUser user) {
        var localDateTime = LocalDateTime.now();
        schedule(user, MessageCode.NF_NERVOUS_SYSTEM_RELOAD_VIDEO, localDateTime);
        schedule(user, MessageCode.NF_WHY_CREATE_NEUROFIT_PHOTO, localDateTime.plusDays(1).withHour(9).withMinute(0));
        schedule(user, MessageCode.NF_WHY_CREATE_NEUROFIT, localDateTime.plusDays(1).withHour(9).withMinute(0).withSecond(1));
        schedule(user, MessageCode.NF_EXERCISE_WITH_BALL, localDateTime.plusDays(1).withHour(15).withMinute(0));
    }

    public void onVideoExercisesWithBallReceived(BotUser user) {
        var localDateTime = LocalDateTime.now();
        schedule(user, MessageCode.NF_EXERCISE_WITH_BALL_VIDEO, localDateTime);
        schedule(user, MessageCode.NF_PROGRAM_CONTENT_PHOTO, localDateTime.plusDays(1).withHour(9).withMinute(0));
        schedule(user, MessageCode.NF_PROGRAM_CONTENT, localDateTime.plusDays(1).withHour(9).withMinute(0).withSecond(1));
        schedule(user, MessageCode.NF_RESULTS_FEEDBACK, localDateTime.plusDays(1).withHour(15).withMinute(0));
        schedule(user, MessageCode.NF_PROMOCODE_OFFER_PHOTO, localDateTime.plusDays(2).withHour(9).withMinute(0));
        schedule(user, MessageCode.NF_PROMOCODE_OFFER, localDateTime.plusDays(2).withHour(9).withMinute(0).withSecond(1));
        schedule(user, MessageCode.NF_PROMOCODE_REMINDER_PHOTO, localDateTime.plusDays(3).withHour(9).withMinute(0));
        schedule(user, MessageCode.NF_PROMOCODE_REMINDER, localDateTime.plusDays(3).withHour(9).withMinute(0).withSecond(1));
    }

    private void schedule(BotUser user, MessageCode messageCode, LocalDateTime scheduledAt) {
        PreparedMessage message = preparedMessageRepository.findByCode(messageCode)
                .orElseThrow(() -> new IllegalStateException("Message not found: " + messageCode));

        ScheduledMessage scheduledMessage = new ScheduledMessage();
        scheduledMessage.setUser(user);
        scheduledMessage.setPreparedMessage(message);
        scheduledMessage.setScheduledAt(scheduledAt);

        scheduledMessageRepository.save(scheduledMessage);
    }
}
