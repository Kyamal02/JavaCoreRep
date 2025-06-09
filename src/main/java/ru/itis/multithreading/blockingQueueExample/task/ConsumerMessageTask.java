package ru.itis.multithreading.blockingQueueExample.task;


import ru.itis.multithreading.blockingQueueExample.MessageBroker;

import java.util.function.Consumer;

public class ConsumerMessageTask<T> extends MessageBrokerTask<T> {


    public ConsumerMessageTask(MessageBroker<T> messageBroker, int secondTimeOut) {
        super(messageBroker, secondTimeOut);

    }

    @Override
    protected void executeOperation(MessageBroker<T> messageBroker) throws InterruptedException {
        T take = messageBroker.take();
        System.out.println(take + " was consume");
    }
}
