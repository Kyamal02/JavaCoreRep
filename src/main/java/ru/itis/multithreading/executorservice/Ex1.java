package ru.itis.multithreading.executorservice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool(10);


        long start = System.currentTimeMillis();
        extracted(executorService);
        long end = System.currentTimeMillis();
        System.out.println("///");
        System.out.println(end - start + "мс потребовалось");
        System.out.println("///");

        executorService.shutdown();


    }

    private static void extracted(ExecutorService executorService) {
        CountDownLatch countDownLatch = new CountDownLatch(4);

        executorService.execute(() -> {
            int result = 0;
            for (int i = 0; i < 10_000_000; i++) {
                if (i % 2 == 0) {
                    result += i;
                }
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                double res = Math.tan(i * 0.001) + Math.tanh(i * 0.0005);

            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                double res = Math.sqrt(i + 1) + Math.log(i + 2);
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                double x = i * 0.0001;
                double res = Math.sin(x) * Math.cos(x) * Math.sqrt(Math.abs(x)) + Math.log1p(x + 1);
                if (res == Double.MAX_VALUE) {
                    System.out.println("Impossible!");
                }
            }
            countDownLatch.countDown();
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
