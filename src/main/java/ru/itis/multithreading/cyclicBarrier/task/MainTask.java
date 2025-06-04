package ru.itis.multithreading.cyclicBarrier.task;

import java.util.List;

public class MainTask extends CompositeTask<SubTask> {
    public MainTask(int id, List<SubTask> subtasks) {
        super(id, subtasks);
    }

    @Override
    protected void perform(SubTask subtask) {
        new Thread(subtask).start();
    }
}
