package ru.itis.multithreading.deadlock.liveLock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.*;
import static java.lang.Thread.*;

public class LiveLock {

    private static final Lock firstLock = new ReentrantLock();
    private static final Lock secondLock = new ReentrantLock();

    private static final String MESSAGE_TEMPLATE_TRY_TO_ACQUIRE_LOCK = "Thread '%s' is trying to acquire lock '%s' \n";
    private static final String MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK = "Thread '%s' acquire lock '%s'\n";
    private static final String MESSAGE_TEMPLATE_RELEASE_LOCK = "Thread '%s' released lock '%s'\n";



    public static void main(String[] args) {
        Thread firstThread = new Thread(new Task(firstLock, secondLock));
        Thread secondThread = new Thread(new Task(secondLock, firstLock));
        AtomicInteger atomicInteger = new AtomicInteger();
        firstThread.start();
        secondThread.start();
    }

    private static final class Task implements Runnable {
        private final Lock firstLock;
        private final Lock secondLock;

        public Task(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            out.printf(MESSAGE_TEMPLATE_TRY_TO_ACQUIRE_LOCK, currentThread().getName(), "lock1");
            firstLock.lock();
            try {
                out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThread().getName(), "lock1");
                sleep(100);
                while (!secondLock.tryLock()) {
                    sleep(100);
                    firstLock.unlock();
                    out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThread().getName(), "lock1");
                    sleep(100);
                    out.printf(MESSAGE_TEMPLATE_TRY_TO_ACQUIRE_LOCK, currentThread().getName(), "lock1");
                    firstLock.lock();
                    out.printf(MESSAGE_TEMPLATE_SUCCESS_ACQUIRE_LOCK, currentThread().getName(), "lock1");
                    sleep(100);
                }
                try {
                    out.println("Текст");
                } finally {
                    secondLock.unlock();
                }

            } catch (InterruptedException e) {
                currentThread().interrupt();
            } finally {
                firstLock.unlock();

            }
        }
    }


}
