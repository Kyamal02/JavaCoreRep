package ru.itis.multithreading.executorservice.example;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = new ExecutorService(2);
        try {
            executorService.execute(() -> System.out.println("execute task1"));
            executorService.execute(() -> System.out.println("execute task2"));
            executorService.execute(() -> System.out.println("execute task3"));
            executorService.execute(() -> System.out.println("execute task4"));
            executorService.execute(() -> System.out.println("execute task5"));
            executorService.execute(() -> System.out.println("execute task6"));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
