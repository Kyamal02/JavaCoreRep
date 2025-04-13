package ru.itis.generics;

import ru.itis.collections.arrayList.ArrayList;

// должен быть подклассом Number и реализовывать интерфейс Comparable
public class Box<T extends Number & Comparable<T>> {
    // Number не реализует Comparable, но мы сможем использовать методы этого интерфеса
    public double add(T a, T b) {
        //a.compareTo(b);
        return a.doubleValue() + b.doubleValue();
    }


    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        System.out.println(integerBox.add(2, 5));

        Integer integer = new Integer(5);
    }
}
