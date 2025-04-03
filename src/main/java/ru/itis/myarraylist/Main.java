package ru.itis.myarraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        System.out.println(list);

        list.remove(new Integer(12));
        System.out.println(list);

        Integer i = 5;
        Number n = (Integer) i;

        String s = "hi im Kemal 27";

        System.out.println(s.substring(s.indexOf("im"), s.indexOf("7")));
        List<Object> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        read(list1);
    }

    static void read(List<? super Integer> list) {
        Object o = new Object();

    }
}
