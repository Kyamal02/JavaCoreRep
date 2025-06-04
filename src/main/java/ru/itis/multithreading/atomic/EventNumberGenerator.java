package ru.itis.multithreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class EventNumberGenerator {
    private final AtomicInteger value = new AtomicInteger();
    private final int GENERATION_DELTA = 2;

    public int generate() {
        return this.value.getAndAdd(GENERATION_DELTA);
    }

    public int getValue() {
        return value.intValue();
    }

    public int getGENERATION_DELTA() {
        return GENERATION_DELTA;
    }
}
