package ru.itis.multithreading.interruption;

public class ThreadTest2 extends Thread {

    public ThreadTest2() {
        this.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (isInterrupted()) {
                break;
            }
            try {
                Thread.sleep(5000);
                //исключение бросается если поток спит, а его прерывают
                // при этом мы сделали try-catch только внутри фора, поэтому программа продолжит свое выполнение после
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }
            System.out.print("1");
        }
    }

    public static void main(String[] args) {

        Thread thread = new ThreadTest2();

        for (int i = 0; i < 10000; i++) {
            System.out.println("2");
            if (i == 9999) {
                thread.interrupt();
            }
        }
    }
}
