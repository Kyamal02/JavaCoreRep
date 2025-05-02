package ru.itis.multithreading.waitnotify.messagebroker.broker;

import ru.itis.multithreading.waitnotify.messagebroker.consumer.MessageConsumerTask;
import ru.itis.multithreading.waitnotify.messagebroker.model.Message;
import ru.itis.multithreading.waitnotify.messagebroker.producer.MessageProducerTask;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static java.lang.Thread.*;

public class MessageBroker {
    private final Queue<Message> messageQueue = new LinkedList<>();
    private final int MAX_AMOUNT_OF_MESSAGE = 15;

    synchronized public Optional<Message> consume(MessageConsumerTask messageConsumerTask) {
        try {
            while (!isShouldConsume(messageConsumerTask)) {
                String consumerStatus = isShouldConsume(messageConsumerTask) ? "Поток потребителя остановился из-за переполнения очереди " : "Поток потребителя остановился для оптимизации ";
                System.out.println(consumerStatus +
                        " Имя потока: " + currentThread().getName() +
                        " Размер очереди: " + messageQueue.size());
                super.wait();
            }
            final Message result = messageQueue.poll();
            System.out.println(currentThread().getName() + " получил сообщение " + result.getData());
            super.notifyAll();
            return Optional.of(result);
        } catch (InterruptedException e) {
            currentThread().interrupt();
            return Optional.empty();
        }
    }

    synchronized public void produce(final Message message, MessageProducerTask messageProducerTask) {
        try {
            while (!isShouldProduce(messageProducerTask)) {
                String producerStatus = isShouldProduce(messageProducerTask) ? "Поток поставщика остановился из-за переполнения очереди " : "Поток поставщика остановился для оптимизации ";
//                System.out.println(producerStatus +
//                        " Имя потока: " + currentThread().getName() +
//                        " Размер очереди: " + messageQueue.size());
                super.wait();
            }
            messageQueue.add(message);
            System.out.println(currentThread().getName() + " отправил сообщение " + message.getData());

            super.notifyAll();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    private boolean isShouldConsume(MessageConsumerTask messageConsumerTask) {
        return !messageQueue.isEmpty()
                && getQueueSize() >= messageConsumerTask.getMinimalAmountMessagesToConsume();

    }

    private boolean isShouldProduce(MessageProducerTask messageProducerTask) {
        return getQueueSize() <= messageProducerTask.getMaxAmountMessagesToProduce() && MAX_AMOUNT_OF_MESSAGE >= getQueueSize();
    }

    private int getQueueSize() {
        return messageQueue.size();
    }

}
