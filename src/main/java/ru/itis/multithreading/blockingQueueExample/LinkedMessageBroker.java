package ru.itis.multithreading.blockingQueueExample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedMessageBroker<T> extends MessageBroker<T> {
    public LinkedMessageBroker(LinkedBlockingQueue<T> blockingQueue) {
        super(blockingQueue);
    }
}
