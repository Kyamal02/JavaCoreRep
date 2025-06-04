package ru.itis.multithreading.cyclicBarrier.task.factory;

import ru.itis.multithreading.cyclicBarrier.task.Task;

import java.util.concurrent.CyclicBarrier;

public abstract class TaskFactory {
    protected long nextId;
    protected final long secondDuration;
    protected final CyclicBarrier cyclicBarrier;

    public TaskFactory(long secondDuration, CyclicBarrier cyclicBarrier) {
        this.secondDuration = secondDuration;
        this.cyclicBarrier = cyclicBarrier;
    }

    public Task create() {
        return create(nextId++, secondDuration, cyclicBarrier);
    }

    protected abstract Task create(long nextId, long secondDuration, CyclicBarrier cyclicBarrier);
}
