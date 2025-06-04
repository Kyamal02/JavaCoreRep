package ru.itis.multithreading.cyclicBarrier.task.factory;

import ru.itis.multithreading.cyclicBarrier.task.LeafTask;
import ru.itis.multithreading.cyclicBarrier.task.Task;

import java.util.concurrent.CyclicBarrier;

public class LeafTaskFactory extends TaskFactory {
    public LeafTaskFactory(long secondDuration, CyclicBarrier cyclicBarrier) {
        super(secondDuration, cyclicBarrier);
    }

    @Override
    protected LeafTask create(long nextId, long secondDuration, CyclicBarrier cyclicBarrier) {
        return new LeafTask(nextId, secondDuration, cyclicBarrier);
    }
}
