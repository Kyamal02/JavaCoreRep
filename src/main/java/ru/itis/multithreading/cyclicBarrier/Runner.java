package ru.itis.multithreading.cyclicBarrier;

import ru.itis.multithreading.cyclicBarrier.task.LeafTask;
import ru.itis.multithreading.cyclicBarrier.task.MainTask;
import ru.itis.multithreading.cyclicBarrier.task.SubTask;
import ru.itis.multithreading.cyclicBarrier.task.factory.LeafTaskFactory;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.*;

public class Runner {
    public static void main(String[] args) {
        final int parties = 3;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, () -> System.out.println("************"));

        LeafTaskFactory leafTaskFactory = new LeafTaskFactory(2, cyclicBarrier);

        final int amountOfLeafTask = 3;

        SubTask subTask1 = new SubTask(0, createLeafTaskList(leafTaskFactory, amountOfLeafTask));
        SubTask subTask2 = new SubTask(1, createLeafTaskList(leafTaskFactory, amountOfLeafTask));
        SubTask subTask3 = new SubTask(3, createLeafTaskList(leafTaskFactory, amountOfLeafTask));

        MainTask mainTask = new MainTask(1, Arrays.asList(new SubTask[]{
                subTask1, subTask2, subTask3
        }));

        mainTask.perform();
    }

    private static List<LeafTask> createLeafTaskList(LeafTaskFactory factory, final int taskCount) {
        return range(0, taskCount)
                .mapToObj((i) -> (LeafTask) factory.create())
                .collect(Collectors.toList());
    }

}
