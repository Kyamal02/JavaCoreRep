package ru.itis.multithreading.volatileexample;

import static java.lang.System.*;
import static java.lang.Thread.*;

public class Runnable {

    public static void main(String[] args) throws InterruptedException {
        final PrintingTask printingTask = new PrintingTask();
        final Thread printingThread = new Thread(printingTask);

        printingThread.start();

        sleep(5000);

        printingTask.setShouldPrint(false);
        out.println("Printing should be stopped");
    }

    private final static class PrintingTask implements java.lang.Runnable {
        private volatile boolean shouldPrint = true;

        public void setShouldPrint(boolean shouldPrint) {
            this.shouldPrint = shouldPrint;
        }

        @Override
        public void run() {

            try {
                while (this.shouldPrint) {
                    out.println("I`m working");
                    sleep(100);
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }

}
