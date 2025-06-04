package ru.itis.multithreading.lock.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterGuardedByReadWriteLock extends AbstractCounter {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    @Override
    protected Lock getReadLock() {
        return readWriteLock.readLock();
    }

    @Override
    protected Lock getWriteLock() {
        return readWriteLock.writeLock();
    }
}
