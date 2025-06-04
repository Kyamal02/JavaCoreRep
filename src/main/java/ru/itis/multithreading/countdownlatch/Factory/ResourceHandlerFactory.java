package ru.itis.multithreading.countdownlatch.Factory;

import ru.itis.multithreading.countdownlatch.ResourceHandler;
import ru.itis.multithreading.countdownlatch.ResourceTask;

import java.util.concurrent.CountDownLatch;

public class ResourceHandlerFactory extends ResourceTaskFactory{
    @Override
    protected ResourceTask create(long id, CountDownLatch countDownLatch) {
        return new ResourceHandler(id, countDownLatch);
    }
}
