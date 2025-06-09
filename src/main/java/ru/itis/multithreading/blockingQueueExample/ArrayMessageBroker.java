package ru.itis.multithreading.blockingQueueExample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayMessageBroker<T> extends MessageBroker<T> {
    public ArrayMessageBroker(ArrayBlockingQueue<T> blockingQueue) {
        super(blockingQueue);
    }
}
