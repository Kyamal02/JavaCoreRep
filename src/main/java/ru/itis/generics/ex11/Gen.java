package ru.itis.generics.ex11;

import java.util.Objects;

public class Gen<T> {
    T obj;

    T[] vals;

    public Gen(T o, T[] nums) {
        this.obj = o;
        this.vals = nums;
    }
}
