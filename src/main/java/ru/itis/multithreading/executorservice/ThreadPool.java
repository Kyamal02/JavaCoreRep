package ru.itis.multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int counter = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(counter);
                }
            });
        }
        // потоки без данного вызова работают в режиме ожидания
        executorService.shutdown();

    }
}
