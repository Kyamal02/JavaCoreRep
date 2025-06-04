package ru.itis.multithreading.atomic;


import static java.util.Arrays.*;
import static java.util.stream.IntStream.*;

public class Runner {
    public static void main(String[] args) {
        final EventNumberGenerator generator = new EventNumberGenerator();

        final int taskGenerationCounts = 10000;


        final Runnable runnable = () -> {
            range(0, taskGenerationCounts).forEach(i -> generator.generate());
        };

        final int amountOfGenerationThreads = 5;

        final Thread[] threads = createThreads(runnable, amountOfGenerationThreads);

        startThreads(threads);

        waitUntilThreadsFinish(threads);

        int resultValue = generator.getValue();


        if (resultValue == amountOfGenerationThreads * taskGenerationCounts * generator.getGENERATION_DELTA()) {
            System.out.println("Потоки успешно выполнили таск");
        }
    }

    public static Thread[] createThreads(final Runnable task, final int amountThreads) {
        return range(0, amountThreads).mapToObj(i -> new Thread(task)).toArray(Thread[]::new);
    }

    public static void startThreads(Thread[] threads) {
        stream(threads).forEach(Thread::start);
    }

    public static void waitUntilThreadsFinish(Thread[] threads) {
        stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        });
    }
}
