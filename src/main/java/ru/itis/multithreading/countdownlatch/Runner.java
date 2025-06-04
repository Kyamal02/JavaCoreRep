package ru.itis.multithreading.countdownlatch;

import ru.itis.multithreading.countdownlatch.Factory.ResourceHandlerFactory;
import ru.itis.multithreading.countdownlatch.Factory.ResourceLoaderFactory;
import ru.itis.multithreading.countdownlatch.Factory.ResourceTaskFactory;

import java.util.concurrent.CountDownLatch;

import static java.util.stream.IntStream.*;

public class Runner {
    public static void main(String[] args) {
        int resourcesCount = 3;
        final CountDownLatch latch = new CountDownLatch(resourcesCount);

        Thread[] loadThreads = createResourceThreads(resourcesCount, new ResourceLoaderFactory(), latch);

        Thread[] handlerThreads = createResourceThreads(2, new ResourceHandlerFactory(), latch);

        startThreads(handlerThreads);

        startThreads(loadThreads);
    }


    private static Thread[] createResourceThreads(int countThreads, ResourceTaskFactory resourceTaskFactory, CountDownLatch latch) {
        return range(0, countThreads)
                .mapToObj(i -> resourceTaskFactory.create(latch))
                .map(Thread::new)
                .toArray(Thread[]::new);
    }

    private static void startThreads(Thread[] threads) {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
