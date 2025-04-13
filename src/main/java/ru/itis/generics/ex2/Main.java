package ru.itis.generics.ex2;

public class Main {
    public static void main(String[] args) {
        Gen<Integer> iOjb1 = new Gen<>(1);

        Gen2<Integer> iObj2 = new Gen2<>(5);

        if (iOjb1 instanceof Gen) {
            System.out.println("iOjb1 instanceof Gen");
        }

        if (iOjb1 instanceof Gen2) {
            System.out.println("iOjb1 instanceof Gen2");
        }

        if (iObj2 instanceof Gen) {
            System.out.println("iObj2 instanceof Gen");
        }

        if (iObj2 instanceof Gen2) {
            System.out.println("iObj2 instanceof Gen2");
        }

    }
}
