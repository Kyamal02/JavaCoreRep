package ru.itis.generics.ex1;

// тут в классе мы обязаны прописать extends Comparable<T> иначе будет ошибка компиляции
public class MyClass<T extends Comparable<T>> implements MaxMin<T> {
    T[] vals;

    public MyClass(T... vals) {
        this.vals = vals;
    }

    @Override
    public T min() {
        T min = vals[0];
        for (int i = 0; i < vals.length; i++) {
            if (min.compareTo(vals[i]) > 0) {
                min = vals[i];
            }
        }
        return min;
    }

    @Override
    public T max() {
        T max = vals[0];
        for (int i = 0; i < vals.length; i++) {
            if (max.compareTo(vals[i]) < 0) {
                max = vals[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MyClass<Integer> integerMyClass = new MyClass<>(5, 4, 2, 1, 5, 3, 2);
        System.out.println(integerMyClass.max());
        System.out.println(integerMyClass.min());

        Integer i = new Integer(6);

    }
}
