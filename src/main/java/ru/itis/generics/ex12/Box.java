package ru.itis.generics.ex12;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Box<T extends Number> {


    public static void main(String[] args) {
//        Box<Number> umberBox = new Box<>();
//        Box<? extends Integer> box = new Box<Integer>();
//        List<? extends Number> list = new ArrayList<Integer>();
//        ArrayList<Integer>[] integers = new ArrayList[10];


//        Integer number = list.get(0);

//        ArrayList<Number> numbers = new ArrayList<Integer>();
//        numbers.add(5);
//        numbers.add(2.5);
//        Number[] numbers1 = new Integer[5];


//        Object object = new Integer(5);
//        object = 3.15;
//        System.out.println(object);
//
//        Number[] integers1 = new Integer[5];
//        Object[] objects = integers1;
//        objects[0] = "sad";
//        System.out.println(objects[0]);
//
//        ArrayList<Integer>[] arr = new ArrayList[10];
//        arr[0].add("ads");
//        Object[] objects2 = arr;
//        objects2[0] = "fs";

//
//        ArrayList<?>[] arr2 = new ArrayList<?>[10];
//
//        Object[] objects3 = arr2;
//        objects3[0] = "fs";


//
//
//
//        ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[10];
//
//// Сырой тип позволяет записать массив с другим параметром
//        Object[] objArr = arr; // Массивы ковариантны
//        objArr[0] = new ArrayList<String>(); // Компилируется!
//
//// Ошибка во время выполнения
//        arr[0].add(42); // ClassCastException: ArrayList<String> не может содержать Integer


    }

    static <U> void test(ArrayList<? extends U> src, ArrayList<? super U> dsc) {
        dsc.addAll(src);
    }
}
