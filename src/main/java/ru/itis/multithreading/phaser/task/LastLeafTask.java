package ru.itis.multithreading.phaser.task;

import java.util.concurrent.Phaser;

import static java.lang.System.out;

public class LastLeafTask extends AbstractLeafTask{
    public LastLeafTask(long id, long secondDuration, Phaser phaser) {
        super(id, secondDuration, phaser);
    }

    @Override
    protected void onFinish(Phaser phaser) {
        out.printf("%s called arriveAndDeregister\n", this);
        phaser.arriveAndDeregister();
    }
}
