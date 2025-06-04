package ru.itis.multithreading.cyclicBarrier.task;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;
import static java.lang.Thread.*;

public class LeafTask extends Task {

    private final long secondDuration;
    private final CyclicBarrier cyclicBarrier;

    public LeafTask(long id, long secondDuration, CyclicBarrier cyclicBarrier) {
        super(id);
        this.secondDuration = secondDuration;
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void perform() {
        try {
            out.printf("%s is starting\n", this);
            TimeUnit.SECONDS.sleep(secondDuration);
            out.printf("%s has finished\n", this);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        } catch (BrokenBarrierException cause) {
            throw new RuntimeException(cause);
        }
    }
}
