package ru.itis.multithreading.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.*;
import static java.lang.Thread.*;

public class DeadLock3 {

    public static void main(String[] args) {
        Lock firstLock = new ReentrantLock();
        Lock secondLock = new ReentrantLock();

        // Чтобы не было deadlock требуется передавать в одном порядке
        Thread firstThread = new Thread(new Task(firstLock, secondLock));
        Thread secondThread = new Thread(new Task(secondLock, firstLock));

        firstThread.start();
        secondThread.start();

    }


    private static class Task implements Runnable {
        private static final String MESSAGE_TEMPLATE_TRY_TO_ACQUIRE_LOCK = "Thread '%s' is trying to acquire lock '%s' \n";
        private static final String MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK = "Thread '%s' acquire lock '%s'\n";
        private static final String MESSAGE_TEMPLATE_RELEASE_LOCK = "Thread '%s' released lock '%s'\n";

        private static final String nameFirstLock = "firstLock";
        private static final String nameSecondLock = "secondLock";

        private final Lock firstLock;
        private final Lock secondLock;

        public Task(final Lock firstLock, final Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            final String currentThreadName = currentThread().getName();
            out.printf(MESSAGE_TEMPLATE_TRY_TO_ACQUIRE_LOCK, currentThreadName, nameFirstLock);
            firstLock.lock();
            try {
                out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, nameFirstLock);
                sleep(200);

                out.printf(MESSAGE_TEMPLATE_TRY_TO_ACQUIRE_LOCK, currentThreadName, nameSecondLock);
                secondLock.lock();
                try {
                    out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThreadName, nameSecondLock);
                } finally {
                    secondLock.unlock();
                    out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, nameSecondLock);
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            } finally {
                firstLock.unlock();
                out.printf(MESSAGE_TEMPLATE_RELEASE_LOCK, currentThreadName, nameFirstLock);
            }

        }
    }
}