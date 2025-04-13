package ru.itis.generics.ex1;

// ограничили T
public interface MaxMin<T extends Comparable<T>> {
    T min();

    T max();
}

