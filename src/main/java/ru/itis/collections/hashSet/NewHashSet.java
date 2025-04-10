package ru.itis.collections.hashSet;

import ru.itis.collections.Set;
import ru.itis.collections.hashMap.HashMap;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.function.Predicate;


public class NewHashSet<E> implements Set<E> {


    private HashMap<E, Object> hashMap = new HashMap<>();

    private final Object PRESENT = new Object();

    @Override
    public boolean add(E element) {
        //заменит ключ одинаковый и вернет Object
        return hashMap.put(element, PRESENT) == null;
    }

    @Override
    public boolean remove(E element) {
        return hashMap.remove(element) != null;
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean contains(E element) {
        return hashMap.containsKey(element);
    }


    @Override
    public Iterator<E> iterator() {

        return hashMap.keySet().iterator();
    }


    public static void main(String[] args) {


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

