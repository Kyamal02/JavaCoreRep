package ru.itis.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;

public interface Map<K, V> {

    V put(K key, V value);

    V remove(K key);

    V get(K key);

    int size();

    Set<K> keySet();

    Collection<V> values();

    boolean containsKey(Object key);

    void clear();


}
