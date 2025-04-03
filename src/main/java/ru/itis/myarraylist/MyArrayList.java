package ru.itis.myarraylist;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {
    private Object[] array = new Object[10];
    private int size = 0;

    public E get(int index) {
        return (E) array[index];
    }

    public void add(E element) {
        if (size == array.length) {
            array = grow();
        }
        array[size] = element;
        size++;
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size] = null;
        size--;
    }

    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                remove(i);
                return;
            }
        }
    }

    private Object[] grow() {
        return Arrays.copyOf(array, array.length * 2);
    }

    private Object[] manualGrow() {
        Object[] newArray = new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {

        return "MyArrayList{" +
                "array=" + Arrays.toString(Arrays.copyOf(array, size)) + "}";
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {

        private int cursor = 0;

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
