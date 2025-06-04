package ru.itis.multithreading.semaphore;

import ru.itis.multithreading.semaphore.entity.Connection;
import ru.itis.multithreading.semaphore.pool.AbstractPool;
import ru.itis.multithreading.semaphore.pool.ConnectionPool;
import ru.itis.multithreading.semaphore.task.AbstractPoolTask;
import ru.itis.multithreading.semaphore.task.ConnectionPoolTask;


import static java.lang.Thread.*;
import static java.util.Arrays.*;
import static java.util.stream.IntStream.range;

public class Runner {
    public static void main(String[] args) {
        AbstractPool<Connection> pool = new ConnectionPool(2);

        Thread[] threads = createThreads(20, new ConnectionPoolTask(pool), pool);

        startThreads(threads);

        waitUntilThreadsFinish(threads);
    }

    private static <T> Thread[] createThreads(int amountOfThreads, AbstractPoolTask<T> abstractPoolTask,
                                              AbstractPool<T> abstractPool) {
        return range(0, amountOfThreads).mapToObj((i) -> new Thread(abstractPoolTask)).toArray(Thread[]::new);
    }

    private static void startThreads(Thread[] threads) {
        stream(threads).forEach(Thread::start);
    }

    private static void waitUntilThreadsFinish(Thread[] threads) {
        stream(threads).forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        });
    }

}
