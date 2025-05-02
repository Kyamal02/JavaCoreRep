package ru.itis.multithreading.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

import static java.lang.Thread.UncaughtExceptionHandler;


public class Task {
    public static void main(String[] args) {

        UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Вывод исключения в методе uncaughtException " + e.getMessage());
            }
        };

        ThreadFactory threadFactory = new DaemonThreadWithUncaughtExceptionHandlerFactory(uncaughtExceptionHandler);

        threadFactory.newThread(new InnerClass()).start();
    }


    private static final class DaemonThreadWithUncaughtExceptionHandlerFactory implements ThreadFactory {

        private final UncaughtExceptionHandler uncaughtExceptionHandler;

        private DaemonThreadWithUncaughtExceptionHandlerFactory(UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            return thread;
        }
    }

    private static class InnerClass implements Runnable {
        @Override
        public void run() {
            System.out.println("Поток: " + Thread.currentThread().getName());
        }
    }
}
