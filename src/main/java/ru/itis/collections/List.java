package ru.itis.collections;

public interface List<E> extends Collection<E> {
    E get(int index);


    void add(int index, E element);

    boolean add(E element);

    boolean remove(E element);

    void clear();

    int size();

    boolean contains(E element);

    boolean remove(int index);

}
