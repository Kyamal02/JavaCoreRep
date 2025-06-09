package ru.itis.multithreading.blockingQueueExample.task;

import ru.itis.multithreading.blockingQueueExample.MessageBroker;

import java.util.function.Supplier;

public class ProducerMessageTask<T> extends MessageBrokerTask<T> {
    private final Supplier<T> supplier;

    public ProducerMessageTask(MessageBroker<T> messageBroker, int secondTimeOut, Supplier<T> supplier) {
        super(messageBroker, secondTimeOut);
        this.supplier = supplier;
    }

    @Override
    protected void executeOperation(MessageBroker<T> messageBroker) throws InterruptedException {
        messageBroker.put(supplier.get());
        System.out.println(supplier.get() + " was produced" );
    }
}
