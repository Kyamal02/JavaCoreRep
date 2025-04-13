package ru.itis.generics.ex5;

public class BoundedWildCard {
    // подойдет любой подкласс ? extends Spaces.TwoD
    static void showXY(Coords<?> c) {
//        c.cords[2] = 2; // точный тип T неизвестен (может быть любой подкласс TreeD).
//        Компилятор запрещает запись в такой массив, так как это может нарушить типобезопасность.
        for (int i = 0; i < c.cords.length; i++) {
            System.out.print("x: " + c.cords[i].x + " y: " + c.cords[i].y);
            System.out.println();
        }
    }

    static void showXYZ(Coords<? extends Spaces.TreeD> c) {
//        c.cords[2] = 2; // точный тип T неизвестен (может быть любой подкласс TreeD).
//        Компилятор запрещает запись в такой массив, так как это может нарушить типобезопасность.
        for (int i = 0; i < c.cords.length; i++) {
            System.out.print("x: " + c.cords[i].x + " y: " + c.cords[i].y + " z: " + c.cords[i].z);
            System.out.println();
        }
    }

}
