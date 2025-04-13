package ru.itis.generics.ex6;

import ru.itis.collections.arrayList.ArrayList;

public class CollectionTest<T extends Number> {

    // не является типобезопасным, так как можем передать все что угодно
    public void printArrayList1(ArrayList<T> arrayList) {
        arrayList.add((T) new Integer(5));
    }

    public void printArrayList2(ArrayList<?> arrayList) {
//        arrayList.add(new Integer(5)); так не сработает так как во время компиляции мы не можем быть уверены в типе
    }

    public void printArrayList3(ArrayList<? extends T> arrayList) {
//        arrayList.add(new Integer(5)); аналогично

        T t = arrayList.get(0);
        Number t2 = arrayList.get(0);
        Integer t3 = (Integer) arrayList.get(0);
    }

    public void addInArrayList(ArrayList<? super Integer> arrayList) {
        arrayList.add(new Integer(5));
//        arrayList.add(new Object()); нельзя, тк может прийти лист интеджеров
//        arrayList.add(2.5f); нельзя
        Object object = arrayList.get(0);
        Integer object1 = (Integer) arrayList.get(0); // не типобезопасно
    }


}
