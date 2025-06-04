package ru.itis.multithreading.semaphore.entity;

import java.util.Objects;


public class Connection {
    private final long id;
    private boolean autoCommit;

    public Connection(long id, boolean autoCommit) {
        this.id = id;
        this.autoCommit = autoCommit;
    }

    public long getId() {
        return id;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return id == that.id && autoCommit == that.autoCommit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, autoCommit);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[id = " + this.id + ", autoCommit = " + this.autoCommit + "]";
    }
}
