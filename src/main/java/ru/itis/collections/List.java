package ru.itis.collections;

public interface List<E> {
    E get(int index);

    void add(E element);

    void add(int index, E element);

    boolean remove(E element);

    boolean remove(int index);

    int size();

    void clear();
}
