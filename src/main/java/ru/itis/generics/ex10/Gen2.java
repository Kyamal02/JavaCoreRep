package ru.itis.generics.ex10;
// компилятор способен точно определить какой метод вызывать
public class Gen2<T extends Number, V> {
    T obj1;
    V obj2;

    void set(T o) {
        obj1 = o;
    }

    void set (V o){
        obj2 = o;
    }

    public static void main(String[] args) {
        Gen2<Integer, String> gen2 = new Gen2<>();

        Gen2<Number, Number> gen = new Gen2<>();
//        gen.set(5); не скомпилируется из за неоднозначности
    }

}
