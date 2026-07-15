package com.hyemink.probability_simulator.domain;

import java.util.Random;

public enum Coin {
    HEAD,
    TAIL;

    private static final Random RANDOM = new Random();

    public static Coin toss() {
        return RANDOM.nextBoolean()
                ? HEAD
                : TAIL;
    }
}
