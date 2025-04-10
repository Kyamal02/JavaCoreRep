package ru.itis.collections;

public interface Collection<E> extends Iterable<E> {
    boolean add(E element);

    boolean remove(E element);

    void clear();

    int size();

    boolean contains(E element);

}
