package ru.itis.multithreading.uncaughtExeption;

import static java.lang.Thread.*;

public class Task implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Исключение произошло в потоке " + t.getName() + " исключение: " + e.getMessage());
                e.printStackTrace();

            }
        });
        thread.start();

        // подефолту для каждого потока будет
        setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Исключение произошло в потоке " + t.getName() + " исключение: " + e.getMessage());
                e.printStackTrace();

            }
        });
    }

    @Override
    public void run() {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i;
        }

        System.out.println(ints[12]);
    }
}
