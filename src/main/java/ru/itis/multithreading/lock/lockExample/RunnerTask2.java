package ru.itis.multithreading.lock.lockExample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.lang.System.*;
import static java.util.Arrays.*;

public class RunnerTask2 {
    public static void main(String[] args) {

        final Counter counter = new Counter();

        final Thread incrementingThread = new Thread(task(counter, value -> counter.increment(), 10));
        final Thread decrementingThread = new Thread(task(counter, value -> counter.decrement(), 10));

        startThreads(incrementingThread, decrementingThread);
        waitUntilFinish(incrementingThread, decrementingThread);

        out.println("Counter: " + counter.value);
    }

    private static void startThreads(Thread... threads) {
        stream(threads).forEach(Thread::start);
    }


    private static void waitUntilFinish(Thread... threads) {
        stream(threads).forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Runnable task(final Counter counter, final IntConsumer operation, final int times) {
        return () -> {
            counter.lock();
            try {
                IntStream.range(0, times).forEach(operation);
            } finally {
                counter.unlock();
            }
        };
    }

    private static final class Counter {
        private final Lock lock = new ReentrantLock();
        int value;

        public void lock() {
            this.lock.lock();
            printMessageWithCurrentThreadArgument("Thread '%s' locked counter. \n");
        }

        public void increment() {
            value++;
            printMessageWithCurrentThreadArgument("Thread 's' incremented counter \n");
        }

        public void decrement() {
            value--;
            printMessageWithCurrentThreadArgument("Thread 's' decremented counter \n");
        }

        public void unlock() {
            lock.unlock();
        }

        private static void printMessageWithCurrentThreadArgument(String message) {
            out.printf(message, Thread.currentThread().getName());
        }
    }

}
