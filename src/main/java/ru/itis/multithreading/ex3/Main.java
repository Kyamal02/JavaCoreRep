package ru.itis.multithreading.ex3;

import ru.itis.collections.List;
import ru.itis.collections.arrayList.ArrayList;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        ATM atm = new ATM(1_000_000L);

        Account account1 = new Account(false, "Кямаль", 500_000L);
        Account account2 = new Account(false, "Андрей", 500_000L);
        Account account3 = new Account(false, "Миша", 500_000L);
        Account account4 = new Account(false, "Рустем", 500_000L);

        List<Thread> threads = new ArrayList<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.inc();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.inc();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.inc();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.inc();
            }
        });

        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);


        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(counter.getValue());

    }

}
