package ru.itis.multithreading.cyclicBarrier.task;

public abstract class Task implements Runnable {
    private final long id;

    public Task(long id) {
        this.id = id;
    }

    public abstract void perform();

    @Override
    public String toString() {
        return this.getClass().getName() + "[id = " + this.id + "]";
    }

    @Override
    public void run() {
        this.perform();
    }
}
