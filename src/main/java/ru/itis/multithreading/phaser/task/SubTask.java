package ru.itis.multithreading.phaser.task;

import java.util.Deque;
import java.util.List;

public class SubTask extends CompositeTask<AbstractLeafTask> {

    public SubTask(long id, List<AbstractLeafTask> tasks) {
        super(id, tasks);
    }

    @Override
    protected void perform(List<AbstractLeafTask> tasks) {
        tasks.forEach(Task::perform);
    }
}
