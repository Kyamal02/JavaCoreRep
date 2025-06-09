package ru.itis.multithreading.сoncurrentHashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiThreadLetterCounter extends LetterCounter {


    public MultiThreadLetterCounter(int subtaskCounter) {
        super(subtaskCounter);
    }

    @Override
    protected Map<Character, Integer> createAccumulator() {
        return new ConcurrentHashMap<>();
    }

    @Override
    protected void execute(Stream<Subtask> subtaskStream) {
        List<Thread> threads = subtaskStream
                .map(subtask -> new Thread(() -> {
                    System.out.printf("%s is starting\n", Thread.currentThread().getName());
                    subtask.execute();
                }))
                .collect(Collectors.toList());

        // Запускаем все потоки
        threads.forEach(Thread::start);

        // Ждем завершения всех потоков
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
