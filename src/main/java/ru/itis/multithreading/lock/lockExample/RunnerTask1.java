package ru.itis.multithreading.lock.lockExample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class RunnerTask1 {
    public static void main(String[] args) {
        final EvenNumberGenerator evenNumberGenerator = new EvenNumberGenerator();

        final Runnable generateTask = () -> IntStream.range(0, 100).forEach(i -> System.out.println(evenNumberGenerator.generate()));

        final Thread firstThread = new Thread(generateTask);
        final Thread secondThread = new Thread(generateTask);
        final Thread thirdThread = new Thread(generateTask);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
    }

    private static final class EvenNumberGenerator {
        private int previousGenerator;
        private final Lock lock = new ReentrantLock();

        public EvenNumberGenerator() {
            this.previousGenerator = -2;
        }

        public int generate() {
            return this.lock.tryLock() ? onSuccessAcquireLock() : onFailAcquireLock();
        }

        private int onSuccessAcquireLock() {
            try {
                return this.previousGenerator += 2;
            } finally {
                lock.unlock();
            }
        }

        private int onFailAcquireLock() {
            System.out.printf("Thread 's' did`t acquire lock. \n", Thread.currentThread().getName());
            throw new RuntimeException();
        }
    }
}
