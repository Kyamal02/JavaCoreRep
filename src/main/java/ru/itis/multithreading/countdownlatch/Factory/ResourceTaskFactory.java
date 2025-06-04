package ru.itis.multithreading.countdownlatch.Factory;

import ru.itis.multithreading.countdownlatch.ResourceTask;

import java.util.concurrent.CountDownLatch;

public abstract class ResourceTaskFactory {
    private long nextId;

    public final ResourceTask create(CountDownLatch countDownLatch) {
        return create(nextId++, countDownLatch);
    }

    protected abstract ResourceTask create(final long id, final CountDownLatch countDownLatch);
}
