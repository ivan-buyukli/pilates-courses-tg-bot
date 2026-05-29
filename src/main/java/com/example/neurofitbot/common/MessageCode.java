package com.example.neurofitbot.common;

public enum MessageCode {

    NF_WELCOME_INTRO("Welcome intro message"),

    NF_GIFT_NEURO_PROPOSAL("Free neuro exercises gift proposal"),

    NF_GIFT_NEURO("Free neuro exercises gift"),

    NF_STAY_IN_BOT("Stay in bot onboarding message"),

    NF_WHAT_IS_NEUROFIT("Explanation of NeuroFit concept"),

    NF_WHAT_IS_NEUROFIT_VIDEO("Explanation of NeuroFit concept video"),

    NF_NERVOUS_SYSTEM_RELOAD("Nervous system overload explanation"),

    NF_NERVOUS_SYSTEM_RELOAD_VIDEO("Nervous system overload explanation video"),

    NF_WHY_CREATE_NEUROFIT("Why Neurofit created?"),

    NF_WHY_CREATE_NEUROFIT_VOICE("Why Neurofit created voice"),

    NF_EXERCISE_WITH_BALL("Exercises with a ball"),

    NF_EXERCISE_WITH_BALL_VIDEO("Exercises with a ball video"),

    NF_PROGRAM_CONTENT("NeuroFit program content overview"),

    NF_RESULTS_FEEDBACK("Client feedback and results"),

    NF_PROMOCODE_OFFER("Promo code offer message"),

    NF_PROMOCODE_REMINDER("Promo code reminder");

    private final String description;

    MessageCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
