package ru.itis.multithreading.blockingQueueExample;

import java.util.concurrent.BlockingQueue;

public class MessageBroker<T> {
    private final BlockingQueue<T> blockingQueue;

    public MessageBroker(BlockingQueue<T> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void put(T value) throws InterruptedException {
        blockingQueue.put(value);
    }

    public T take() throws InterruptedException {
        return blockingQueue.take();
    }

}
