package ru.itis.multithreading.phaser.task;

public abstract class Task implements Runnable {
    private final long id;

    public Task(long id) {
        this.id = id;
    }

    public abstract void perform();

    @Override
    public String toString() {
        return Thread.currentThread().getName() + "[id = " + id + "]";
    }

    @Override
    public void run() {
        perform();
    }
}
