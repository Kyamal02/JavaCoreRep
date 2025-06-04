package ru.itis.multithreading.lock.readwritelock;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.Thread.*;
import static java.util.Arrays.*;
import static java.util.stream.IntStream.*;
// При отсутствии задержи на чтении
// CounterGuardedByLock - 2 592 401 794
// CounterGuardedByReadWriteLock - 386 819 372

// При добавлении задержки на чтение(getValue) 1 сек
// CounterGuardedByLock - 109
// CounterGuardedByReadWriteLock - 2496

// Добавим справедливости и задержку
// CounterGuardedByLock - 109
// CounterGuardedByReadWriteLock - 2419
public class Runner {
    public static void main(String[] args) {
        try {
            testCounter(CounterGuardedByReadWriteLock::new);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testCounter(final Supplier<? extends AbstractCounter> counterFactory) throws InterruptedException {
        final AbstractCounter counter = counterFactory.get();

        final int amountOfThreadsGettingValue = 50;
        final ReadingValueTask[] readingTasks = createReadingTasks(counter, amountOfThreadsGettingValue);
        final Thread[] readingThreads = mapToThreads(readingTasks);


        final Runnable incrementingCounterTask = createIncrementingCounterTask(counter);
        final Thread[] incrementingThreads = createThreads(incrementingCounterTask, 2);

        startThreads(readingThreads);
        startThreads(incrementingThreads);

        sleep(60_000);

        interruptThreads(readingThreads);
        interruptThreads(incrementingThreads);

        waitUntilFinish(readingThreads);

        System.out.println(findTotalAmountOfThreads(readingTasks));

    }

    private static ReadingValueTask[] createReadingTasks(final AbstractCounter counter, final int amountCounter) {
        return range(0, amountCounter)
                .mapToObj(i -> new ReadingValueTask(counter))
                .toArray(ReadingValueTask[]::new);
    }

    private static Thread[] mapToThreads(final Runnable[] tasks) {
        return stream(tasks)
                .map(Thread::new)
                .toArray(Thread[]::new);
    }

    private static void incrementCounter(final AbstractCounter counter) {
        counter.increment();

    }

    private static Runnable createIncrementingCounterTask(final AbstractCounter counter) {
        return () -> {
            try {
                while (!currentThread().isInterrupted()) {
                    sleep(1000);
                    incrementCounter(counter);
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        };
    }

    private static Thread[] createThreads(final Runnable task, final int amountOfThreads) {
        return range(0, amountOfThreads)
                .mapToObj(t -> new Thread(task))
                .toArray(Thread[]::new);
    }

    private static void startThreads(final Thread[] threads) {
        forEach(threads, Thread::start);
    }

    private static void interruptThreads(final Thread[] threads) {
        forEach(threads, Thread::interrupt);
    }

    private static void forEach(final Thread[] threads, final Consumer<Thread> action) {
        stream(threads).forEach(action);
    }

    private static void waitUntilFinish(final Thread[] threads) {
        forEach(threads, Runner::waitUntilFinish);
    }

    private static void waitUntilFinish(final Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    private static long findTotalAmountOfThreads(final ReadingValueTask[] tasks) {
        (stream(tasks).mapToLong(ReadingValueTask::getAmountOfReads)).forEach(System.out::println);
        return stream(tasks)
                .mapToLong(ReadingValueTask::getAmountOfReads).sum();
    }

    public static final class ReadingValueTask implements Runnable {
        private final AbstractCounter counter;
        private long amountOfReads;

        public ReadingValueTask(AbstractCounter counter) {
            this.counter = counter;
        }

        public long getAmountOfReads() {
            return amountOfReads;
        }

        @Override
        public void run() {
                while (!currentThread().isInterrupted()) {
                    counter.getValue();
                    amountOfReads++;
                }

        }

    }
}
