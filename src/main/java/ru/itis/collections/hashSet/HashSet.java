package ru.itis.collections.hashSet;

import ru.itis.collections.Set;

import java.util.ArrayList;
import java.util.Iterator;


public class HashSet<E> implements Set<E> {
    private int size = 0;

    private static final int INITIAL_CAPACITY = 16;
    private Entry<E>[] hashTable = new Entry[INITIAL_CAPACITY];
    static final float DEFAULT_LOAD_FACTOR = 0.75f;


    @Override
    public boolean add(E element) {
        if (size > hashTable.length * DEFAULT_LOAD_FACTOR) {
            growHashTable();
        }
        return addElementInToHashTable(element, getElementPosition(element));
    }

    @Override
    public boolean remove(E element) {
        return removeElementInToHashTable(element, getElementPosition(element));
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        int elementPosition = getElementPosition(element);
        if (hashTable[elementPosition] == null) {
            return false;
        }
        Entry<E> current = hashTable[elementPosition];
        while (current != null) {
            if (element == current.element || element != null && element.equals(current.element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private int getElementPosition(E element) {
        if (element == null) {
            return 0; // null всегда в бакете 0
        }
        int hash = element.hashCode();
        // сдвиг требуется для перемешивания первых 16 бит с последними 16.
        // чтобы результат был более случайным
        return (hash ^ (hash >>> 16)) & (hashTable.length - 1);
//      return element.hashCode() % hashTable.length;
    }

    private boolean addElementInToHashTable(E element, int position) {
        Entry<E> current = hashTable[position];
        if (current == null) {
            hashTable[position] = new Entry<>(element, null);
        } else {
            Entry<E> previous = null;
            while (current != null) {
                if (element == current.element || element != null && element.equals(current.element)) {
                    return false;
                }
                previous = current;
                current = current.next;
            }
            previous.next = new Entry<>(element, null);
        }
        size++;
        return true;
    }

    public boolean removeElementInToHashTable(E element, int position) {
        if (hashTable[position] == null) {
            return false;
        } else {
            Entry<E> current = hashTable[position];
            Entry<E> previous = null;
            while (current != null) {
                if (element == current.element || element != null && element.equals(current.element)) {
                    if (previous == null) {
                        hashTable[position] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    size--;
                    return true;
                }
                previous = current;
                current = current.next;
            }
        }
        return false;
    }

    private void growHashTable() {
        Entry<E>[] oldHashTable = hashTable;
        int newCapacity = hashTable.length * 2;
        hashTable = (Entry<E>[]) new Entry[newCapacity];

        for (int i = 0; i < oldHashTable.length; i++) {
            if (oldHashTable[i] != null) {
                Entry<E> current = oldHashTable[i];
                while (current != null) {
                    Entry<E> next = current.next;
                    int position = getElementPosition(current.element);
                    current.next = hashTable[position];
                    hashTable[position] = current;
                    current = next;
                }
            }
        }
    }

    @Override
    public Iterator<E> iterator() {

        return new Itr();
    }


    private class Itr implements Iterator<E> {
        int iteratorCounter = 0;
        int index = 0;
        Entry<E> current;

        @Override
        public boolean hasNext() {
            return iteratorCounter != size();
        }

//        @Override
//        public E next() {
//            if (current != null && current.next != null) {
//                current = current.next;
//                iteratorCounter++;
//                return current.element;
//            } else {
//                for (; index < hashTable.length; index++) {
//                    if (hashTable[index] != null) {
//                        current = hashTable[index];
//                        break;
//                    }
//                }
//            }
//            index++;
//            iteratorCounter++;
//            return current.element;
//        }

        @Override
        public E next() {
            while (hashTable[index] == null) {
                index++;
            }
            current = hashTable[index];
            if (current.next == null) {
                iteratorCounter++;
                index++;
                return current.element;
            }
            E result = current.element;
            current = current.next;
            iteratorCounter++;
            return result;
        }

    }


    private static class Entry<E> {
        private E element;
        private Entry<E> next;

        public Entry(E element, Entry<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        Entry<Integer>[] hashTable1 = hashSet.hashTable;

        hashTable1 = new Entry[10];
        hashTable1[0] = new Entry<>(5, null);

//        HashSet<Integer> integerHashSet = new HashSet<>();
//        System.out.println(integerHashSet.add(null));
//        System.out.println(integerHashSet.add(null));
//        System.out.println(integerHashSet.add(Integer.MIN_VALUE));

//        Car car = new Car();
//        System.out.println(car.equals(null));
//        System.out.println(Objects.equals(null, new Car()));
//
//        Integer i = Integer.MAX_VALUE;
//        System.out.println(i.hashCode() % 16);
//        int hash = i.hashCode();
//        hash = hash == 0x80000000 ? 1 : hash;
//        System.out.println((hash ^ (hash >>> 16)) & (16 - 1));

//        ArrayList arrayList = new ArrayList();
//
//        java.util.HashSet<Integer> hashSet = new java.util.HashSet<>();
//        for (int i = 0; i < 100000; i++) {
//            hashSet.add(i);
//        }
//        Iterator<Integer> iterator = hashSet.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

//        HashSet<Integer> integerHashSet = new HashSet<>();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 20_000_000; i++) {
//            integerHashSet.add(i);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end-start+ " мс");
//
//        System.out.println();
//
//        java.util.HashSet<Integer> integerHashSet1 = new java.util.HashSet<>();
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < 20_000_000; i++) {
//            integerHashSet1.add(i);
//        }
//        long end1 = System.currentTimeMillis();
//        System.out.println(end1-start1+ " мс");
//        System.out.println(integerHashSet.hashTable.length);

//
//        java.util.HashSet<String> hashSet = new java.util.HashSet<>();
//        System.out.println(hashSet.add(null));
//        System.out.println(hashSet.add(null));
//        System.out.println(hashSet.contains(null));
    }
}

