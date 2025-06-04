package ru.itis.multithreading.deadlock;

// неверная реализация так как будет переполнение стека сначала
public class DeadLock1 {

    public static void main(String[] args) {
        DeadLock1 main = new DeadLock1();
        DeadLock1.A a = main.new A();
        DeadLock1.B b = main.new B();

    }

    class A extends Thread {
        B b = new B();

        public A() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            start();
        }

        @Override
        public void run() {
            aMethod();
        }

        synchronized void aMethod() {
            b.bMethod();
        }
    }

    class B extends Thread {
        A a = new A();

        public B() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            start();
        }

        @Override
        public void run() {
            bMethod();
        }

        synchronized void bMethod() {
            a.aMethod();
        }
    }
}
