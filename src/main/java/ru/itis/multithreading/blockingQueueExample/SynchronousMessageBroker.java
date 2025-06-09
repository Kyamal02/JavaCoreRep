package ru.itis.multithreading.blockingQueueExample;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousMessageBroker<T> extends MessageBroker<T>{
    public SynchronousMessageBroker(SynchronousQueue<T> blockingQueue) {
        super(blockingQueue);
    }
}
