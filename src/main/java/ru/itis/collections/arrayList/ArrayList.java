package ru.itis.collections.arrayList;

import ru.itis.collections.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

    private Object[] array = new Object[10];
    private int size = 0;

    //O(1)
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) array[index];
    }

    //O(1) если массив не требуется расширять
    @Override
    public void add(E element) {
        grow();
        array[size] = element;
        size++;

    }

    //O(n)
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    //O(n)
    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
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
        System.arraycopy(array, index+1, array, index, size-index-1);
        array[size] = null;
        size--;
        return true;


    }

    @Override
    public int size() {
        return size;
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
}


