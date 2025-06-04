package ru.itis.multithreading.phaser.task;

import ru.itis.collections.Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public abstract class CompositeTask<S extends Task> extends Task {
    private final List<S> tasks;

    public CompositeTask(long id, List<S> tasks) {
        super(id);
        this.tasks = tasks;
    }


    @Override
    public void perform() {
        perform(tasks);
    }

    protected abstract void perform(List<S> tasks);
}
