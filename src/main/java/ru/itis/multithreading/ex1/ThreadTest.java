package ru.itis.multithreading.ex1;

public class ThreadTest {

    private static int SIZE = 10_000_000;

    public static void main(String[] args) {
        withConcurrency();
        withOutConcurrency();
    }

    private static void withConcurrency() {
        long start = System.currentTimeMillis();
        float[] floats = new float[SIZE];
        float[] floats1 = new float[SIZE / 2];
        float[] floats2 = new float[SIZE / 2];
        System.arraycopy(floats, 0, floats1, 0, SIZE / 2);
        System.arraycopy(floats, SIZE / 2, floats2, 0, SIZE / 2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < floats1.length; i++) {
                floats1[i] = 1f;
            }
            float f;
            for (int i = 0; i < floats1.length; i++) {
                f = i;
                floats1[i] = (float) (floats1[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < floats2.length; i++) {
                floats2[i] = 1f;
            }

            float f;
            for (int i = 0; i < floats2.length; i++) {
                f = i;
                floats2[i] = (float) (floats2[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.arraycopy(floats1, 0, floats, 0, SIZE / 2);
            System.arraycopy(floats2, 0, floats, SIZE / 2, SIZE / 2);
            long end = System.currentTimeMillis();
            System.out.println("C потоками заняло: " + ((end - start)) + " мс");
        }

    }

    private static void withOutConcurrency() {
        long start = System.currentTimeMillis();
        Float[] floats = new Float[SIZE];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = 1f;
        }
        float f;
        for (int i = 0; i < floats.length; i++) {
            f = i;
            floats[i] = (float) (floats[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 2));
        }
        long end = System.currentTimeMillis();
        System.out.println("Без потоков заняло: " + ((end - start)) + " мс");
    }


}
