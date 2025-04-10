package ru.itis.collections;

import ru.itis.collections.arrayList.ArrayList;

import java.util.*;
import java.util.Set;


public class Car implements Comparable<Car> {
    private String brand;
    private Integer number;
    TreeSet<CarOwner> integers = new TreeSet<>();


    public Car(String brand, Integer number) {
        this.brand = brand;
        this.number = number;
        integers.add(new CarOwner(1, "sf", "dsf"));
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(brand, car.brand) && Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, number);
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            integers.add(0, i);
        }
        System.out.println(integers.get(11));
        System.out.println(integers);

    }


    @Override
    public int compareTo(Car o) {
        return brand.compareTo(o.brand);
    }
}
