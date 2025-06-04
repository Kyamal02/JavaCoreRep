package ru.itis.multithreading.lock.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterGuardedByLock extends AbstractCounter {
    private final Lock lock = new ReentrantLock(true);

    @Override
    protected Lock getReadLock() {
        return lock;
    }

    @Override
    protected Lock getWriteLock() {
        return lock;
    }
}
