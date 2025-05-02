package ru.itis.multithreading.deadlock;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        Main.A a = main.new A();
        Main.B b = main.new B();

        a.setB(b);
        b.setA(a);

        a.start();
        b.start();
    }

    class A extends Thread {
        private B b;

        @Override
        public void run() {
            method();
        }

        void method() {
            b.bMethod();
        }

        synchronized void aMethod() {
            // чтобы каждый поток успел в свой монитор
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            b.bMethod();
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    class B extends Thread {
        private A a;

        @Override
        public void run() {
            method();
        }

        void method() {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            a.aMethod();
        }

        synchronized void bMethod() {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            a.aMethod();
        }

        public void setA(A a) {
            this.a = a;
        }
    }
}
