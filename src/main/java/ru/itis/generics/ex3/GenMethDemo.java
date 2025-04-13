package ru.itis.generics.ex3;

public class GenMethDemo {

    static <T extends Comparable<T>, V extends T> boolean isIn(T element, V[] array) {
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                System.out.println("Элемент " + element + " найден в массиве");
                return true;
            }
        }
        return false;
    }

    static <T extends Comparable<T>> void printElement(T t) {
        System.out.println(t);
    }

    static <T extends Comparable<T>> void printArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        int a = 2;
        int[] d = {
                2, 3, 4, 0, 5
        };
//        isIn(a, d); // не сработает так как для массива автоупаковка не сработает
        // тут происходит автоупаковка.
        int c = 5;
        printElement(c);

//        printArray(d); ошибка компиляции

        Integer[] b = {
                2, 3, 4, 0, 5
        };

        printArray(b); // сработает


        isIn(a, b);
    }
}
