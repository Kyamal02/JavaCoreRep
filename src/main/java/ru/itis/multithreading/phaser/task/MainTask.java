package ru.itis.multithreading.phaser.task;


import java.util.List;

public class MainTask extends CompositeTask<SubTask> {


    public MainTask(long id, List<SubTask> tasks) {
        super(id, tasks);
    }


    @Override
    protected void perform(List<SubTask> tasks) {
        tasks.forEach(task -> new Thread(task).start());
    }
}
