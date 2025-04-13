package ru.itis.generics.task;

public class Orange extends Fruit {

    public Orange() {
        super.weight = 1.5;
    }

    @Override
    public Double getWeight() {
        return super.weight.doubleValue();
    }
}
