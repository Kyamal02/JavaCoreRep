package ru.itis.multithreading.countdownlatch.Factory;

import ru.itis.multithreading.countdownlatch.ResourceLoader;
import ru.itis.multithreading.countdownlatch.ResourceTask;

import java.util.concurrent.CountDownLatch;

public class ResourceLoaderFactory extends ResourceTaskFactory {

    private long nextSecondDuration = 1;

    @Override
    protected ResourceTask create(long id, CountDownLatch countDownLatch) {
        return new ResourceLoader(id, countDownLatch, nextSecondDuration++);
    }
}
