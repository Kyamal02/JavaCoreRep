package ru.itis.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;

import static java.lang.System.*;
import static java.lang.Thread.*;

public class ResourceHandler extends ResourceTask {

    public ResourceHandler(long id, CountDownLatch latch) {
        super(id, latch);
    }

    @Override
    protected void run(CountDownLatch latch) {
        try {
            latch.await();
            out.printf("Resources were handled by %s\n", this);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
