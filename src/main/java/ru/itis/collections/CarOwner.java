package ru.itis.collections;

import java.util.Objects;

public class CarOwner {
    private int id;
    private String firstName;
    private String lastName;

    public CarOwner(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "CarOwner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarOwner)) return false;
        CarOwner carOwner = (CarOwner) o;
        return id == carOwner.id && Objects.equals(firstName, carOwner.firstName) && Objects.equals(lastName, carOwner.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}
