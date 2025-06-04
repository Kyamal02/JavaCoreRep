package ru.itis.multithreading.lock.conditionExample;

import java.util.Arrays;
import java.util.concurrent.locks.*;

import static java.lang.Thread.*;

public class BoundedBuffer<T> {

    private final Object[] elements;
    private int size = 0;

    private final Lock lock = new ReentrantLock();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Condition isFullCondition = lock.newCondition();
    private final Condition isEmptyCondition = lock.newCondition();


    public BoundedBuffer(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public void add(T element) {
        lock.lock();
        try {
            while (isFull()) {
                System.out.println("Очередь полная, ждем когда появится место");
                isFullCondition.await();
            }
            elements[size] = element;
            size++;
            isEmptyCondition.signal();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        } finally {
            System.out.println("Добавлен элемент: " + this);
            lock.unlock();
        }
    }

    public T get() {
        lock.lock();
        try {
            while (isEmpty()) {
                System.out.println("Очередь пуста, ждем элемент");
                isEmptyCondition.await();
            }
            T result = (T) elements[size - 1];
            elements[size - 1] = null;
            size--;
            isFullCondition.signal();
            return result;
        } catch (InterruptedException e) {
            currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            System.out.println("Элемент получен: " + this);
            lock.unlock();
        }
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "BoundedBuffer{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}
