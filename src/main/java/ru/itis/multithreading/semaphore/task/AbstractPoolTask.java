package ru.itis.multithreading.semaphore.task;

import ru.itis.multithreading.semaphore.pool.AbstractPool;

import static java.lang.System.*;


public abstract class AbstractPoolTask<T> implements Runnable {
    AbstractPool<T> pool;

    public AbstractPoolTask(AbstractPool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        T object = pool.acquire();
        try {
            out.printf("%s был захвачен\n", object);
            handle(object);
        } finally {
            out.printf("%s возвращается в пул\n", object);
            pool.release(object);
        }
    }

    protected abstract void handle(T object);
}
