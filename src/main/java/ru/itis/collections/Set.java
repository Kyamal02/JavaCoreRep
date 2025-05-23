package ru.itis.collections;


public interface Set<E> extends Collection<E> {
    boolean add(E element);

    boolean remove(E element);

    void clear();

    int size();

    boolean contains(E element);
}
