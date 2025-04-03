package ru.itis.immutable;

public class MutableClass {
    public String name;

    public MutableClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MutableClass{" +
                "name='" + name + '\'' +
                '}';
    }
}
