package ru.itis.multithreading.exchanger;

import ru.itis.multithreading.exchanger.task.ConsumerTask;
import ru.itis.multithreading.exchanger.task.ProducingTask;

import java.util.Queue;
import java.util.concurrent.Exchanger;

public class Runner {
    public static void main(String[] args) {
        Exchanger<Queue<ExchangeObject>> exchanger = new Exchanger<>();

        ConsumerTask consumerTask = new ConsumerTask(exchanger);
        ProducingTask producingTask = new ProducingTask(exchanger, 3);

        Thread consumerThread = new Thread(consumerTask);
        consumerThread.start();

        Thread producerThread = new Thread(producingTask);
        producerThread.start();
    }
}
