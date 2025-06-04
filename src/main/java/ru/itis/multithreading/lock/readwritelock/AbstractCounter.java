package ru.itis.multithreading.lock.readwritelock;

import javax.swing.text.html.Option;
import java.util.OptionalLong;
import java.util.concurrent.locks.Lock;

import static java.lang.System.*;
import static java.lang.Thread.*;

public abstract class AbstractCounter {
    private long value;

    public final OptionalLong getValue() {
        final Lock lock = this.getReadLock();
        lock.lock();
        try {
            sleep(1000);
            //            out.println("Поток: " + currentThread().getName() + " получил value: " + value);
            return OptionalLong.of(value);
        } catch (InterruptedException e) {
            currentThread().interrupt();
            return OptionalLong.empty();
        } finally {
            lock.unlock();
        }
    }

    public final void increment() {
        final Lock lock = this.getWriteLock();
        lock.lock();
        try {
            value++;
            //            out.println("Поток: " + currentThread().getName() + " инкрементировал value: " + value);
        } finally {
            lock.unlock();
        }
    }

    protected abstract Lock getReadLock();

    protected abstract Lock getWriteLock();
}
