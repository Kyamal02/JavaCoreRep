package ru.itis.multithreading.waitnotify.messagebroker.producer;

import ru.itis.multithreading.waitnotify.messagebroker.model.Message;

public class MessageFactory {
    private volatile int messageIndex;

    public MessageFactory(int messageIndex) {
        this.messageIndex = messageIndex;
    }

    public Message create() {
        return new Message("Сообщение номер: " + incrementMessageIndex());
    }

    private synchronized int incrementMessageIndex() {
        return messageIndex++;
    }
}
