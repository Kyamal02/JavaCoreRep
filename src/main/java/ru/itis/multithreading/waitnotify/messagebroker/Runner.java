package ru.itis.multithreading.waitnotify.messagebroker;

import ru.itis.multithreading.waitnotify.messagebroker.broker.MessageBroker;
import ru.itis.multithreading.waitnotify.messagebroker.consumer.MessageConsumerTask;
import ru.itis.multithreading.waitnotify.messagebroker.producer.MessageFactory;
import ru.itis.multithreading.waitnotify.messagebroker.producer.MessageProducerTask;

import java.util.Arrays;
import java.util.concurrent.ThreadFactory;

import static java.util.Arrays.*;

public class Runner {

    public static void main(String[] args) {

        final MessageBroker messageBroker = new MessageBroker();

        Thread consumerThread1 = new Thread(new MessageConsumerTask(messageBroker, 0));
        Thread consumerThread2 = new Thread(new MessageConsumerTask(messageBroker, 6));
        Thread consumerThread3 = new Thread(new MessageConsumerTask(messageBroker, 11));

        MessageFactory messageFactory = new MessageFactory(0);

        Thread producerThread1 = new Thread(new MessageProducerTask(messageBroker, messageFactory, 5));
        Thread producerThread2 = new Thread(new MessageProducerTask(messageBroker, messageFactory, 10));
        Thread producerThread3 = new Thread(new MessageProducerTask(messageBroker, messageFactory, 15));


        startThreads(consumerThread1, consumerThread2, consumerThread3,
                producerThread1, producerThread2, producerThread3);
    }

    public static void startThreads(Thread... threads) {
        stream(threads).forEach(Thread::start);
    }

}
