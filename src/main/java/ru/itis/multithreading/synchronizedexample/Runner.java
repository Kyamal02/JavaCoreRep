package ru.itis.multithreading.synchronizedexample;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;


public class Runner {
    private static int counterFirst;
    private static int counterSecond;

    private static final Object firstMonitor = new Object();
    private static final Object secondMonitor = new Object();

    public static void main(String[] args) {
        Thread firstIncrementCounterThread = createIncrementingCounterThread(100, value -> incrementFirstCounter());
        Thread secondIncrementCounterThread = createIncrementingCounterThread(200, value -> incrementSecondCounter());

        startThreads(firstIncrementCounterThread, secondIncrementCounterThread);

        waitingUntilAreCompleted(firstIncrementCounterThread, secondIncrementCounterThread);

        System.out.println(counterFirst + " " + counterSecond);

    }

    private static Thread createIncrementingCounterThread(final int incrementAmount, final IntConsumer intConsumer) {
        return new Thread(() -> IntStream.range(0, incrementAmount).forEach(intConsumer));
    }


    private static void incrementFirstCounter() {
        synchronized (firstMonitor) {
            counterFirst++;
        }
    }

    private static void incrementSecondCounter() {
        synchronized (secondMonitor) {
            counterSecond++;
        }
    }

    private static void startThreads(Thread... threads) {
        stream(threads).forEach(Thread::start);
    }

    private static void waitingUntilAreCompleted(Thread... threads) {
        stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

}
