package ru.itis.collections.hashMap;

import ru.itis.collections.Collection;
import ru.itis.collections.Map;
import ru.itis.collections.Set;
import ru.itis.collections.arrayList.ArrayList;
import ru.itis.collections.hashSet.HashSet;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    private final int DEFAULT_INITIAL_CAPACITY = 16;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size = 0;

    private Entry<K, V>[] hashTable = new Entry[DEFAULT_INITIAL_CAPACITY];

    @Override
    public V put(K key, V value) {
        if (hashTable.length * DEFAULT_LOAD_FACTOR < size) {
            grow();
        }

        return putValueInToHashTable(getElementPosition(key), key, value);
    }

    @Override
    public V remove(K key) {
        return removeElementByKey(key);
    }

    @Override
    public V get(K key) {
        return getValueByKey(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        Entry<K, V> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    keyset.add(current.key);
                    current = current.next;
                }
            }
        }
        return keyset;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        Entry<K, V> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    values.add(current.value);
                    current = current.next;
                }
            }
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        int elementPosition = getElementPosition((K) key);
        Entry<K, V> current = hashTable[elementPosition];

        while (current != null) {
            if (Objects.equals(key, current.key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }

    private int getElementPosition(K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return (hash ^ (hash >>> 16)) & hashTable.length - 1;
    }

    private V putValueInToHashTable(int position, K key, V value) {
        Entry<K, V> current = hashTable[position];
        if (current == null) {
            hashTable[position] = new Entry<>(key, value, null);
            size++;
            return null;
        }
        Entry<K, V> previous = null;
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                V prevVal = current.value;
                current.value = value;
                return prevVal;
            }
            previous = current;
            current = current.next;
        }
        previous.next = new Entry<>(key, value, null);
        size++;
        return null;
    }

    private V removeElementByKey(K key) {
        int elementPosition = getElementPosition(key);
        Entry<K, V> current = hashTable[elementPosition];
        Entry<K, V> previous = null;
        V value = null;
        if (current == null) {
            return null;
        }
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                value = current.value;
                if (previous == null) {
                    hashTable[elementPosition] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    private V getValueByKey(K key) {
        Entry<K, V> current = hashTable[getElementPosition(key)];
        while (current != null) {
            if (Objects.equals(current.key, key))
                return current.value;
            current = current.next;
        }
        return null;
    }

    private void grow() {
        Entry<K, V>[] oldHashTable = hashTable;
        hashTable = new Entry[hashTable.length * 2];

        for (int i = 0; i < oldHashTable.length; i++) {
            if (oldHashTable[i] != null) {
                Entry<K, V> current = oldHashTable[i];
                while (current != null) {
                    Entry<K, V> next = current.next;
                    int keyPosition = getElementPosition(current.key);
                    current.next = hashTable[keyPosition];
                    hashTable[keyPosition] = current;
                    current = next;
                }
            }
        }
    }


    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}

