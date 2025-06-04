package ru.itis.multithreading.cyclicBarrier.task;


import java.util.List;

public class SubTask extends CompositeTask<LeafTask> {
    public SubTask(int id, List<LeafTask> subtasks) {
        super(id, subtasks);
    }

    @Override
    protected void perform(LeafTask subtask) {
        subtask.perform();
    }

}
