package ru.itis.multithreading.waitnotify.ex2;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new BlockingQueue();
        AtomicInteger counter = new AtomicInteger();
        // работает так же
//        java.util.concurrent.BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        blockingQueue.take();
                        System.out.println("потребитель: " + index + " счетчик: " + counter.getAndIncrement());
                    }
                }
            }).start();
        }


        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    blockingQueue.add(() -> System.out.println("."));
                }
            }
        }).start();


    }
}
