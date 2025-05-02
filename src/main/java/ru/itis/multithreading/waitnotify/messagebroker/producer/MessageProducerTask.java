package ru.itis.multithreading.waitnotify.messagebroker.producer;

import ru.itis.multithreading.waitnotify.messagebroker.broker.MessageBroker;

import static java.lang.Thread.*;

public class MessageProducerTask implements Runnable {
    private final MessageBroker messageBroker;
    private final MessageFactory messageFactory;
    private final int maxAmountMessagesToProduce;

    public MessageProducerTask(MessageBroker messageBroker, MessageFactory messageFactory, int maximalAmountMessagesToProduce) {
        this.messageBroker = messageBroker;
        this.messageFactory = messageFactory;
        this.maxAmountMessagesToProduce = maximalAmountMessagesToProduce;
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                messageBroker.produce(this.messageFactory.create(), this);
                sleep(400);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    public int getMaxAmountMessagesToProduce() {
        return maxAmountMessagesToProduce;
    }
}
