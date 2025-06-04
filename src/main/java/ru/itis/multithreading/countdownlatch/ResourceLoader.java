package ru.itis.multithreading.countdownlatch;

import javax.management.timer.Timer;
import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;
import static java.lang.Thread.*;

public class ResourceLoader extends ResourceTask {

    private final long secondDuration;

    public ResourceLoader(long id, CountDownLatch latch, long secondDuration) {
        super(id, latch);
        this.secondDuration = secondDuration;
    }

    @Override
    protected void run(CountDownLatch latch) {
        try {
            out.printf("%s is loading some resource\n", this);
            TimeUnit.SECONDS.sleep(secondDuration);
            out.printf("Some resource was loaded by %s\n", this);
            latch.countDown();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
