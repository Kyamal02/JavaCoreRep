package ru.itis.multithreading.phaser.task;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public abstract class AbstractLeafTask extends Task {
    private final long secondDuration;
    private final Phaser phaser;

    public AbstractLeafTask(long id, long secondDuration, Phaser phaser) {
        super(id);
        this.secondDuration = secondDuration;
        this.phaser = phaser;
    }

    @Override
    public void perform() {
        try {
            out.printf("%s is starting\n", this);
            TimeUnit.SECONDS.sleep(secondDuration);
            out.printf("%s has finished\n", this);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        } finally {
            onFinish(this.phaser);
        }
    }

    protected abstract void onFinish(final Phaser phaser);
}
