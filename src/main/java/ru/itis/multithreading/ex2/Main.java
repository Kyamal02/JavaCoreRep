package ru.itis.multithreading.ex2;

import ru.itis.collections.List;
import ru.itis.collections.arrayList.ArrayList;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(1_000_000L);
        System.out.println(atm.getTotalBalance());

        Account account1 = new Account(false, "Кямаль", 500_000L);
        Account account2 = new Account(false, "Андрей", 500_000L);
        Account account3 = new Account(false, "Миша", 500_000L);
        Account account4 = new Account(false, "Рустем", 500_000L);

        List<Thread> threads = new ArrayList<>();


        Thread thread1 = new Thread(() -> {
            atm.withdrawCash(500_000L, account1);
        });

        Thread thread2 = new Thread(() -> {
            atm.withdrawCash(500_000L, account2);
        });

        Thread thread3 = new Thread(() -> {
            atm.withdrawCash(500_000L, account3);
        });

        Thread thread4 = new Thread(() -> {
            atm.withdrawCash(500_000L, account4);
        });

        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);

//        try {
//            for (Thread thread : threads) {
//                thread.start();
//                thread.join();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

}
