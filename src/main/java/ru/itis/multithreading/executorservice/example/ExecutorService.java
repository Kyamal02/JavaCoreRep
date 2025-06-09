package ru.itis.multithreading.executorservice.example;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

import static java.lang.Thread.*;
import static java.util.stream.IntStream.*;

public class ExecutorService {
    //реалищация может быть любой
    private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();

    public ExecutorService(final int threadCount) {
        startThreads(threadCount);
    }

    private void startThreads(final int threadCount) {
        range(0, threadCount)
                .mapToObj(i -> new Thread(new TaskExecutor(tasks)))
                .forEach(Thread::start);
    }

    public void execute(Runnable runnable) throws InterruptedException {
        tasks.put(runnable);
    }

    private final class TaskExecutor implements Runnable {

        private BlockingQueue<Runnable> tasks;

        public TaskExecutor(BlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.printf("%s is run new task\n", currentThread().getName());
                    tasks.take().run();
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
            }
        }
    }
}
