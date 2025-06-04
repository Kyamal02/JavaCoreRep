package ru.itis.multithreading.phaser;


import ru.itis.multithreading.phaser.task.LastLeafTask;
import ru.itis.multithreading.phaser.task.LeafTask;
import ru.itis.multithreading.phaser.task.MainTask;
import ru.itis.multithreading.phaser.task.SubTask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Phaser;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;


public class Runner {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                out.println();
                out.printf("Thread %s\n", currentThread().getName());
                out.printf("Current phase: %d\n", phase);
                out.printf("Current parties: %d\n", registeredParties);
                out.println();
                return super.onAdvance(phase, registeredParties);
            }
        };

        SubTask subTask1 = new SubTask(1,
                Arrays.asList(
                        new LeafTask(1, 2, phaser)
                        , new LeafTask(2, 2, phaser),
                        new LastLeafTask(3, 2, phaser)));

        SubTask subTask2 = new SubTask(2,
                Arrays.asList(
                        new LeafTask(4, 2, phaser)
                        , new LastLeafTask(5, 2, phaser)));

        SubTask subTask3 = new SubTask(3, List.of(
                new LastLeafTask(6, 2, phaser)));

        List<SubTask> subTasks = Arrays.asList(subTask1, subTask2, subTask3);

        MainTask mainTask = new MainTask(1, subTasks);
        mainTask.perform();
    }

}
