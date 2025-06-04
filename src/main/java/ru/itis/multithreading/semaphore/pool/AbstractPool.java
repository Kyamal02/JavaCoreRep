package ru.itis.multithreading.semaphore.pool;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.*;

public abstract class AbstractPool<T> {

    private final List<PoolObject<T>> poolObjects;
    private final Semaphore semaphore;

    public AbstractPool(final Supplier<T> objectSupplier, final int size) {
        this.poolObjects = createPoolObjects(objectSupplier, size);
        this.semaphore = new Semaphore(size);
    }

    private static <T> List<PoolObject<T>> createPoolObjects(final Supplier<T> objectSupplier, final int size) {
        return range(0, size)
                .mapToObj(i -> new PoolObject<T>(objectSupplier.get(), false))
                .collect(Collectors.toList());
    }

    public final T acquire()  {
        semaphore.acquireUninterruptibly();
        return acquireObject();
    }

    private synchronized T acquireObject() {
        return poolObjects.stream()
                .filter((o) -> !o.isIssued)
                .findFirst()
                .map(AbstractPool::markAsIssued)
                .map(PoolObject::getObject)
                .orElseThrow(IllegalStateException::new);
    }

    private static <T> PoolObject<T> markAsIssued(final PoolObject<T> poolObject) {
        poolObject.setIssued(true);
        return poolObject;
    }

    public void release(T object) {
        if (releaseObject(object)) {
            semaphore.release();
        }
    }

    private synchronized boolean releaseObject(T object) {
        return poolObjects.stream()
                .filter((poolObject) -> poolObject.isIssued)
                .filter(poolObject -> Objects.equals(object, poolObject.object))
                .findFirst()
                .map((this::cleanPoolObject))
                .isPresent();
    }


    private PoolObject<T> cleanPoolObject(final PoolObject<T> poolObject) {
        poolObject.setIssued(false);
        cleanObject(poolObject.object);
        return poolObject;
    }

    protected abstract void cleanObject(final T object);


    private static final class PoolObject<T> {
        private final T object;
        private boolean isIssued;

        public PoolObject(T object, boolean issued) {
            this.object = object;
            this.isIssued = issued;
        }

        public T getObject() {
            return object;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void setIssued(boolean issued) {
            this.isIssued = issued;
        }
    }


}
