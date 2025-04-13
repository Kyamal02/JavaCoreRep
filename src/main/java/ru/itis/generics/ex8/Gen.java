package ru.itis.generics.ex8;

public class Gen<T> {
    T obj;

    public Gen(T obj) {
        this.obj = obj;
    }

    T getObj() {
        return obj;
    }
}
