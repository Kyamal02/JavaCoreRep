package ru.itis.multithreading.deadlock;

public class DeadLock2 {

    public static void main(String[] args) {
        Main main = new Main();
        Main.A a = main.new A();
        Main.B b = main.new B();

        a.setB(b);

    }

    class A {
        private B b;

        public void setB(B b) {
            this.b = b;
        }
    }

    class B {
        private A a;

        public void setA(A a) {
            this.a = a;
        }
    }
}
