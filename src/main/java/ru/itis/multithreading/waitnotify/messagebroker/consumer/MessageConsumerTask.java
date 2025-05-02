package ru.itis.multithreading.waitnotify.messagebroker.consumer;

import ru.itis.multithreading.waitnotify.messagebroker.broker.MessageBroker;
import ru.itis.multithreading.waitnotify.messagebroker.model.Message;

import java.util.Optional;

import static java.lang.Thread.*;

public class MessageConsumerTask implements Runnable {

    private final MessageBroker messageBroker;
    private final int minimalAmountMessagesToConsume;

    public MessageConsumerTask(MessageBroker messageBroker, int minimalAmountMessagesToConsume) {
        this.messageBroker = messageBroker;
        this.minimalAmountMessagesToConsume = minimalAmountMessagesToConsume;
    }

    @Override
    public void run() {
        try {
            while (!currentThread().isInterrupted()) {
                Optional<Message> optionalMessage = messageBroker.consume(this);
                optionalMessage.orElseThrow(MessageConsumerException::new);
                sleep(100);
            }
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    public int getMinimalAmountMessagesToConsume() {
        return minimalAmountMessagesToConsume;
    }
}
