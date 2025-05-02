package ru.itis.multithreading.join;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest2 {
    static List<Integer> integers;
    private static final int test = 5;

    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {
            integers = new ArrayList<>();
        });

        Thread thread2 = new Thread(() -> {
//            Thread.currentThread().interrupt(); выброситься исключения, тк join проверяет is interrupted
            try {
                //ждем, чтобы в данном потоке не было NullPointerException
                thread1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 1_000_000; i++) {
                integers.add(i);
            }
            System.out.println(integers.size());
        });

        thread1.start();
        thread2.start();
    }

}
