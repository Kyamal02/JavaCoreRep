package ru.itis.multithreading.cyclicBarrier.task;


import java.util.List;

public abstract class CompositeTask<S extends Task> extends Task {
    private final List<S> subtasks;

    public CompositeTask(int id, List<S> subtasks) {
        super(id);
        this.subtasks = subtasks;
    }

    @Override
    public void perform() {
        for (S task : subtasks) {
            perform(task);
        }
    }

    protected abstract void perform(final S subtask);


}
