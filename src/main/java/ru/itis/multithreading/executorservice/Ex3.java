package ru.itis.multithreading.executorservice;

import java.util.concurrent.*;

public class Ex3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        executorService.execute(() -> {
            try {
                while (true) {
                    System.out.print("...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        StringBuilder stringBuilder = new StringBuilder("");

        Future<StringBuilder> name = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    stringBuilder.append("Кямаль");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }, stringBuilder);

//        Future<String> name = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws InterruptedException {
//                Thread.sleep(5000);
//                return "Кямаль";
//            }
//        });

        Future<Integer> age = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                Thread.sleep(5000);
                return 22;
            }
        });


        try {
            System.out.print(name.get());
            System.out.print(age.get());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
