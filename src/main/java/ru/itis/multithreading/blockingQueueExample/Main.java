package ru.itis.multithreading.blockingQueueExample;

import ru.itis.multithreading.blockingQueueExample.task.ConsumerMessageTask;
import ru.itis.multithreading.blockingQueueExample.task.ProducerMessageTask;


import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;


public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();
        MessageBroker<Integer> messageBroker = new SynchronousMessageBroker<>((SynchronousQueue) blockingQueue);

        startProducing(messageBroker);
        startProducing(messageBroker);
        startProducing(messageBroker);

        startConsuming(messageBroker);
        startConsuming(messageBroker);
        startConsuming(messageBroker);

    }

    private static void startProducing(MessageBroker<Integer> messageBroker) {
        new Thread(new ProducerMessageTask<>(messageBroker, getRandomNumber(), Main::getRandomNumber)).start();
    }

    private static void startConsuming(MessageBroker<Integer> messageBroker) {
        new Thread(new ConsumerMessageTask<>(messageBroker, getRandomNumber())).start();
    }

    private static int getRandomNumber() {
        return new Random().nextInt(10);
    }
}
