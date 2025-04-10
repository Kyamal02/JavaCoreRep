package ru.itis.collections.arrayList;

import ru.itis.collections.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) array[index];
    }

    @Override
    public boolean add(E element) {
        grow();
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    //O(n)
    @Override
    public boolean remove(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size] = null;
        size--;
        return true;


    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size(); i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    //O(n)
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    private void grow() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }


    @Override
    public String toString() {
        return "ArrayList{" + Arrays
                .toString(Arrays.stream(array)
                        .filter(a -> a != null)
                        .toArray())
                + "}";
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return size != cursor;
        }

        @Override
        public E next() {
            E element = (E) array[cursor];
            cursor++;
            return element;
        }
    }
}


