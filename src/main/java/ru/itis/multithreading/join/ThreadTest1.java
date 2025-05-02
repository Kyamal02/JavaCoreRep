package ru.itis.multithreading.join;

import ru.itis.collections.List;
import ru.itis.collections.linkedList.LinkedList;

public class ThreadTest1 {

    public static void main(String[] args) {
        List<Integer> integers1 = new LinkedList<>();
        List<Integer> integers2 = new LinkedList<>();
        List<Integer> integers3 = new LinkedList<>();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                integers1.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                integers2.add(i);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                integers3.add(i);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        //выпадет исключение
//        Thread.currentThread().interrupt();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(integers1.size());
        System.out.println(integers2.size());
        System.out.println(integers3.size());
    }
}
