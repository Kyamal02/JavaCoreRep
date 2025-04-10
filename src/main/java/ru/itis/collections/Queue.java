package ru.itis.collections;

public interface Queue<E> extends Collection<E> {
    boolean add(E element);

    E peek();

    E poll();

}
