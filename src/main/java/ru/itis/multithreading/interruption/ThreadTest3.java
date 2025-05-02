package ru.itis.multithreading.interruption;

public class ThreadTest3 extends Thread {
    public ThreadTest3() {
        this.start();
    }

    @Override
    public void run() {
        //  try-catch на весь фор, теперь, когда будет ошибка, фор не будет выполняться далее
        try {
            for (int i = 0; i < 1000; i++) {
                if (isInterrupted()) {
                    break;
                }
                Thread.sleep(5000);
                System.out.print("1");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Thread thread = new ThreadTest3();

        for (int i = 0; i < 10000; i++) {
            System.out.println("2");
            if (i == 9999) {
                thread.interrupt();
            }
        }
    }
}
