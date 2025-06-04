package ru.itis.multithreading.semaphore.task;

import ru.itis.multithreading.semaphore.entity.Connection;
import ru.itis.multithreading.semaphore.pool.AbstractPool;

import static java.lang.Thread.*;

public class ConnectionPoolTask extends AbstractPoolTask<Connection> {

    public ConnectionPoolTask(AbstractPool<Connection> pool) {
        super(pool);
    }

    @Override
    protected void handle(Connection connection) {
        connection.setAutoCommit(false);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }
}
