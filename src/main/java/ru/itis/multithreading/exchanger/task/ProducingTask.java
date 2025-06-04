package ru.itis.multithreading.exchanger.task;

import ru.itis.multithreading.exchanger.ExchangeObject;
import ru.itis.multithreading.exchanger.ExchangeObjectFactory;

import java.util.Queue;
import java.util.concurrent.Exchanger;

import static java.util.stream.IntStream.*;

public class ProducingTask extends ExchangerTask {

    private final ExchangeObjectFactory exchangeObjectFactory;
    private final int producingObjectCount;

    public ProducingTask(Exchanger<Queue<ExchangeObject>> exchanger,
                         int producingObjectCount) {
        super(exchanger);
        this.exchangeObjectFactory = new ExchangeObjectFactory();
        this.producingObjectCount = producingObjectCount;
    }

    @Override
    protected void handle(Queue<ExchangeObject> objects) {
        range(0, producingObjectCount)
                .mapToObj(i -> exchangeObjectFactory.create())
                .peek(object -> System.out.printf("Object: %s add in queue\n", object))
                .forEach(objects::add);
    }
}
