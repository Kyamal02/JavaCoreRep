package ru.itis.multithreading.сoncurrentHashMap;

import java.time.chrono.MinguoChronology;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.*;

public abstract class LetterCounter {
    private final int subtaskCounter;

    public LetterCounter(int subtaskCounter) {
        this.subtaskCounter = subtaskCounter;
    }

    public final Map<Character, Integer> count(final String input) {
        Map<Character, Integer> accumulator = createAccumulator();

        Stream<Subtask> subtask = createSubtask(accumulator, input);
        execute(subtask);

        return accumulator;
    }

    protected abstract Map<Character, Integer> createAccumulator();

    protected abstract void execute(Stream<Subtask> subtaskStream);

    private Stream<Subtask> createSubtask(Map<Character, Integer> accumulator, String input) {
        int subtaskCharCount = findSubtaskCharCount(input);
        return range(0, subtaskCharCount)
                .mapToObj(i -> createSubTask(accumulator, input, subtaskCharCount, i));
    }

    private int findSubtaskCharCount(final String input) {
        return (int) Math.ceil((double) input.length() / subtaskCounter);
    }

    private static Subtask createSubTask(final Map<Character, Integer> accumulator,
                                         final String input,
                                         final int charCounter,
                                         final int subtaskIndex) {
        int start = subtaskIndex * charCounter;
        int end = Math.min((subtaskIndex + 1) * charCounter, input.length());

        return new Subtask(accumulator, input, start, end);
    }

    protected static final class Subtask {
        private final Map<Character, Integer> accumulator;
        private final String input;
        private final int start;
        private final int end;

        public Subtask(Map<Character, Integer> accumulator,
                       String input,
                       int start,
                       int end) {
            this.accumulator = accumulator;
            this.input = input;
            this.start = start;
            this.end = end;
        }

        public void execute() {
            range(start, end)

                    .map((c) -> input.codePointAt(c))
                    .filter(c -> Character.isLetter(c))
                    .map(c -> Character.toLowerCase(c))
                    .forEach(c -> accumulate(c));
        }

        private void accumulate(final int codePoint) {
            final Character character = (char) codePoint;
            accumulator.merge(character, 1, (oldValue, value) -> oldValue + value);
        }

        //это будет работать не потокобезопасно
//        private void accumulate(final int codePoint) {
//            final Character character = (char) codePoint;
//            final Integer frequency = accumulator.get(character);
//            if (frequency != null) {
//                accumulator.put(character, frequency + 1);
//            } else {
//                accumulator.put(character, 1);
//            }
//        }

    }

}
