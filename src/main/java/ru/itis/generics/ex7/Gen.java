package ru.itis.generics.ex7;

public class Gen {
    private double val;

    public <T extends Number & Comparable<T>> Gen(T arg) {
        this.val = arg.doubleValue();
    }

    public void showVal() {
        System.out.println("val: " + val);
    }

    public static void main(String[] args) {
        Gen gen = new Gen(100);
//        Gen gen2 = new Gen(""); ошибка компиляции


    }
}
