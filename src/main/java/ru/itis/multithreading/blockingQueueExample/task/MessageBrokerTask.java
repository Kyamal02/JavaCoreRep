package ru.itis.multithreading.blockingQueueExample.task;

import ru.itis.multithreading.blockingQueueExample.MessageBroker;


import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public abstract class MessageBrokerTask<T> implements Runnable {
    private final MessageBroker<T> messageBroker;
    private final int secondTimeOut;

    public MessageBrokerTask(MessageBroker<T> messageBroker, int secondTimeOut) {
        this.messageBroker = messageBroker;
        this.secondTimeOut = secondTimeOut;
    }

    @Override
    public void run() {
        try {
            while (true) {
                executeOperation(this.messageBroker);
                TimeUnit.SECONDS.sleep(secondTimeOut);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    protected abstract void executeOperation(MessageBroker<T> messageBroker) throws InterruptedException;
}
