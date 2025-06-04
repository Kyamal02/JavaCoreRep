package ru.itis.multithreading.exchanger;

public class ExchangeObject {
    private final long id;

    public ExchangeObject(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[id = " + this.id + "]";
    }
}
