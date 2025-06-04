package ru.itis.multithreading.exchanger.task;


import ru.itis.multithreading.exchanger.ExchangeObject;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Exchanger;

import static java.lang.Thread.*;

public abstract class ExchangerTask implements Runnable {
    private final Exchanger<Queue<ExchangeObject>> exchanger;
    private Queue<ExchangeObject> objects;

    public ExchangerTask(Exchanger<Queue<ExchangeObject>> exchanger) {
        this.exchanger = exchanger;
        this.objects = new ArrayDeque<>();
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            this.handle(this.objects);
            this.exchange();
        }
    }

    protected abstract void handle(final Queue<ExchangeObject> objects);

    private void exchange() {
        try {
            this.objects = this.exchanger.exchange(this.objects);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
