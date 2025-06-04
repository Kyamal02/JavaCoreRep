package ru.itis.multithreading.exchanger.task;

import ru.itis.multithreading.exchanger.ExchangeObject;

import java.util.Queue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;


public class ConsumerTask extends ExchangerTask {
    public ConsumerTask(Exchanger<Queue<ExchangeObject>> exchanger) {
        super(exchanger);
    }

    @Override
    protected void handle(Queue<ExchangeObject> objects) {
        while (!objects.isEmpty()) {
            System.out.printf("Object: %s consume in queue\n", objects.poll());
        }

    }
}
