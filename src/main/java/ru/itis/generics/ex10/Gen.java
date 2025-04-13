package ru.itis.generics.ex10;

public class Gen<T, V> {
    T obj1;
    V obj2;

    void set (T o){
        obj1 = o;
    }

//    void set (V o){
//        obj2 = o;
//    }

    // не скомпилируются из за неоднозначности
}
