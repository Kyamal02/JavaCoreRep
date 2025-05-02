package ru.itis.multithreading.deadlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MFD {

    public static void main(String[] args) {
        Collection<Object> objects = Collections.synchronizedCollection(new ArrayList<>());
        MFD.print(10);
        MFD.scan(10);
    }

    private static Object printMonitor = new Object();
    private static Object scanMonitor = new Object();

    public static void print(int number) {
        synchronized (printMonitor) {
            for (int i = 0; i < number; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // может только печатать или сканировать
                synchronized (scanMonitor) {
                    System.out.println("Напечатано " + i + " страниц");
                }
            }
        }
    }

    public static void scan(int number) {
        synchronized (scanMonitor) {
            for (int i = 0; i < number; i++) {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // может только печатать или сканировать
                synchronized (printMonitor) {
                    System.out.println("Сканировано " + i + " страниц");
                }
            }
        }
    }
}
