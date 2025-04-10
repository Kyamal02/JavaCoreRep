package ru.itis.immutable;

public class ImmutableClass {
    private final MutableClass mutableClass;

    public ImmutableClass(MutableClass mutableClass) {
        this.mutableClass = new MutableClass(mutableClass.getName());
    }

    public MutableClass getMutableClass() {
        return new MutableClass(mutableClass.getName());

    }

    @Override
    public String toString() {
        return "ImmutableClass{" +
                "mutableClass=" + mutableClass +
                '}';
    }
}
