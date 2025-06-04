package ru.itis.multithreading.semaphore.pool;

import ru.itis.multithreading.semaphore.entity.Connection;

import java.util.function.Supplier;

public class ConnectionPool extends AbstractPool<Connection> {

    public ConnectionPool(int size) {
        super(new ConnectionSupplier(), size);
    }

    @Override
    protected void cleanObject(Connection connection) {
        connection.setAutoCommit(true);
    }

    private static final class ConnectionSupplier implements Supplier<Connection> {
        private long nextConnectionId;

        @Override
        public Connection get() {
            return new Connection(nextConnectionId++, true);
        }
    }
}
