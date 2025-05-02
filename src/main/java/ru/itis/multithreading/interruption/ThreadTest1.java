package ru.itis.multithreading.interruption;

public class ThreadTest1 extends Thread {
    Thread mainThread = Thread.currentThread();

    //чтобы при создании экземпляра сразу же запускался поток
    public ThreadTest1() {
        this.start();
    }

    @Override
    public void run() {
        System.out.println("до " + Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("после " + Thread.currentThread().isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest1 thread1 = new ThreadTest1();

        thread1.join();



//        ThreadTest thread2 = new ThreadTest();
//        thread1.interrupt();
//        System.out.println(Thread.currentThread().isInterrupted());
//        Thread.currentThread().interrupt();
//        System.out.println(Thread.currentThread().isInterrupted());
//        System.out.println(Thread.interrupted());
    }
}
