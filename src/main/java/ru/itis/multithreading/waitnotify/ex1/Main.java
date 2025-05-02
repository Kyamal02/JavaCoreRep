package ru.itis.multithreading.waitnotify.ex1;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer();

        new Thread(printer::printC).start();

        new Thread(printer::printB).start();

        new Thread(printer::printA).start();

    }
}
