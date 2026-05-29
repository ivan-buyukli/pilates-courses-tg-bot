package com.example.neurofitbot.service;

import com.example.neurofitbot.common.MessageCode;
import com.example.neurofitbot.message.PreparedMessage;
import com.example.neurofitbot.message.PreparedMessageRepository;
import com.example.neurofitbot.message.ScheduledMessage;
import com.example.neurofitbot.message.ScheduledMessageRepository;
import com.example.neurofitbot.user.BotUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
        LocalDateTime base = LocalDateTime.now();
        for (Plan plan : START_SCENARIO_PLANS) {
            schedule(user, plan.code, plan.when.apply(base));
        }
    }

    public void onCoordinationVideoButton(BotUser user) {
        scheduleNow(user, MessageCode.NF_GIFT_NEURO);
    }

    public void onVideoForEyesButton(BotUser user) {
        scheduleNow(user, MessageCode.NF_WHAT_IS_NEUROFIT_VIDEO);

    }

    public void onVideoForBreathingButton(BotUser user) {
        scheduleNow(user, MessageCode.NF_NERVOUS_SYSTEM_RELOAD_VIDEO);
    }

    public void onVideoExercisesWithBallButton(BotUser user) {
        scheduleNow(user, MessageCode.NF_EXERCISE_WITH_BALL_VIDEO);
    }

    private void scheduleNow(BotUser user, MessageCode code) {
        schedule(user, code, LocalDateTime.now());
    }

    /**
     * Represents a message scheduling plan: which message and how to compute its scheduled time
     * relative to a base LocalDateTime.
     */
    private record Plan(MessageCode code, Function<LocalDateTime, LocalDateTime> when) {
    }

    private static final List<Plan> START_SCENARIO_PLANS = Arrays.asList(
            new Plan(MessageCode.NF_WELCOME_INTRO, base -> base),
            new Plan(MessageCode.NF_GIFT_NEURO_PROPOSAL, base -> base.plusSeconds(1)),
            new Plan(MessageCode.NF_STAY_IN_BOT, base -> base.plusMinutes(3)),
            new Plan(MessageCode.NF_WHAT_IS_NEUROFIT, base -> base.plusDays(1).withHour(7).withMinute(0)),
            new Plan(MessageCode.NF_NERVOUS_SYSTEM_RELOAD, base -> base.plusDays(1).withHour(13).withMinute(0)),
            new Plan(MessageCode.NF_WHY_CREATE_NEUROFIT_PHOTO, base -> base.plusDays(2).withHour(7).withMinute(0)),
            new Plan(MessageCode.NF_WHY_CREATE_NEUROFIT, base -> base.plusDays(2).withHour(7).withMinute(0).withSecond(1)),
            new Plan(MessageCode.NF_EXERCISE_WITH_BALL, base -> base.plusDays(2).withHour(13).withMinute(0)),
            new Plan(MessageCode.NF_PROGRAM_CONTENT_PHOTO, base -> base.plusDays(3).withHour(7).withMinute(0)),
            new Plan(MessageCode.NF_PROGRAM_CONTENT, base -> base.plusDays(3).withHour(7).withMinute(0).withSecond(1)),
            new Plan(MessageCode.NF_RESULTS_FEEDBACK, base -> base.plusDays(3).withHour(13).withMinute(0)),
            new Plan(MessageCode.NF_PROMOCODE_OFFER_PHOTO, base -> base.plusDays(4).withHour(7).withMinute(0)),
            new Plan(MessageCode.NF_PROMOCODE_OFFER, base -> base.plusDays(4).withHour(7).withMinute(0).withSecond(1)),
            new Plan(MessageCode.NF_PROMOCODE_REMINDER_PHOTO, base -> base.plusDays(5).withHour(7).withMinute(0)),
            new Plan(MessageCode.NF_PROMOCODE_REMINDER, base -> base.plusDays(5).withHour(7).withMinute(0).withSecond(1))
    );

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
