package ru.itis.multithreading.сoncurrentHashMap;

import java.util.Map;

public class Runner {
    private static String text = "abcdefghijk".repeat(1000);
    
    public static void main(String[] args) {
        LetterCounter singleThreadLetterCounterCounter = new SingleThreadLetterCounter();
        Map<Character, Integer> singleCount = singleThreadLetterCounterCounter.count(text);

        LetterCounter multiThreadLetterCounter = new MultiThreadLetterCounter(10);
        Map<Character, Integer> multiCounter = multiThreadLetterCounter.count(text);


        System.out.println(singleCount);
        System.out.println(multiCounter);
    }
}
