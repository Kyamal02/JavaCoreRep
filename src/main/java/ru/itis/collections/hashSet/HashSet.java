package ru.itis.collections.hashSet;

import ru.itis.collections.Set;


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

    private int getElementPosition(E element) {
        if (element == null) {
            return 0; // null всегда в бакете 0
        }
        int hash = element.hashCode();
        hash = hash == 0x80000000 ? 1 : hash;
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
                if (element == null && current.element == null || current.element.equals(element)) {
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
                if (element == null && current.element == null || element != null && current.element.equals(element)) {
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
            Entry<E> current = oldHashTable[i];
            while (current != null) {
                int position = getElementPosition(current.element);
                current.next = hashTable[position];
                hashTable[position] = current;

                current = current.next;
            }
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
//        HashSet<Integer> integerHashSet = new HashSet<>();
//        System.out.println(integerHashSet.add(null));
//        System.out.println(integerHashSet.add(null));
//        System.out.println(integerHashSet.add(Integer.MIN_VALUE));
        Integer i = Integer.MAX_VALUE;
        System.out.println(i.hashCode() % 16);
        int hash = i.hashCode();
        hash = hash == 0x80000000 ? 1 : hash;
        System.out.println((hash ^ (hash >>> 16)) & (16 - 1));

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

