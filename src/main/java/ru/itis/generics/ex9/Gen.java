package ru.itis.generics.ex9;

public class Gen <T> {

    T obj;

    public Gen(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
}
