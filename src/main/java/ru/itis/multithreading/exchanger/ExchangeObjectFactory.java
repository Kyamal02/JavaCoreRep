package ru.itis.multithreading.exchanger;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public final class ExchangeObjectFactory {
    private long nextId;

    public ExchangeObject create() {
        try {
            TimeUnit.SECONDS.sleep(2);
            return new ExchangeObject(nextId++);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
