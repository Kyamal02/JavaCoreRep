package ru.itis.multithreading.lock.conditionExample;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Thread.*;
import static java.util.Arrays.*;

public class RunnableTask {

    public static void main(String[] args) {
        BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(20);

        Stream.iterate(0, i -> i + 1);

        Thread firstAddThread = new Thread(createTask((i) -> {
            boundedBuffer.add(i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 5000));
//        Thread secondAddThread = new Thread(createTask((i) -> boundedBuffer.add(i), 2000));

        Thread firstGetThread = new Thread(createTask((i) -> {
            boundedBuffer.get();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1000));


//        Thread secondGetThread = new Thread(createTask((i) -> boundedBuffer.get(), 2000));

        startThreads(firstAddThread,
                firstGetThread);

        waitUntilThreadsStopped(firstAddThread,
                firstGetThread);


        System.out.println(boundedBuffer.size());
    }

    private static void startThreads(Thread... threads) {
        stream(threads).forEach(Thread::start);
    }

    private static void waitUntilThreadsStopped(Thread... threads) {
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }

    private static <E> Runnable createTask(IntConsumer operation, int times) {
        return () -> {
            IntStream.range(0, times).forEach(operation);
        };
    }
}
