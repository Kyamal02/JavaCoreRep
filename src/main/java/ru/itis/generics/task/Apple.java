package ru.itis.generics.task;

public class Apple extends Fruit {

    public Apple() {
        super.weight = 1;
    }

    @Override
    public Integer getWeight() {
        return super.weight.intValue();
    }
}
