package ru.itis.multithreading.сoncurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SingleThreadLetterCounter extends LetterCounter {
    SingleThreadLetterCounter() {
        super(1);
    }

    @Override
    protected Map<Character, Integer> createAccumulator() {
        return new HashMap<>();
    }

    @Override
    protected void execute(Stream<Subtask> subtaskStream) {
        subtaskStream.forEach((subtask -> subtask.execute()));
    }
}
