package ru.itis.multithreading.waitnotify.ex1;

public class Printer {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";

    private String nextLetter = A;


    synchronized public void printA() {
        checkLetter(A, B);
    }

    synchronized public void printB() {
        checkLetter(B, C);
    }

    synchronized public void printC() {
        checkLetter(C, A);
    }

    private void checkLetter(String a, String b) {
        for (int i = 0; i < 5; i++) {
            while (!nextLetter.equals(a)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.print(a);
            nextLetter = b;
            notifyAll();
        }
    }
}
