package ru.itis.multithreading.lock.conditionExample;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.*;

public class BoundedBuffer<T> {

    private final Object[] elements;
    private int size = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public BoundedBuffer(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public void add(T element) {
        lock.lock();
        try {
            while (size == elements.length) {
                System.out.println("Очередь полная, ждем когда появится место");
                condition.await();
            }
            elements[size] = element;
            size++;
            condition.signalAll();
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
            while (size == 0) {
                System.out.println("Очередь пуста, ждем элемент");
                condition.await();
            }
            T result = (T) elements[size - 1];
            elements[size - 1] = null;
            size--;
            condition.signalAll();
            return result;
        } catch (InterruptedException e) {
            currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            System.out.println("Элемент получен: " + this);
            lock.unlock();
        }
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
