package ru.itis.collections;

import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.linkedList.LinkedList;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListTest {

    List<Car> carList;

    @Before
    public void setUp() {
        carList = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            carList.add(new Car("brand" + i, i));
        }
    }

    @Test
    public void whenAdd100ElementsThenSizeMustBe100() {
        assertEquals(100, carList.size());
    }

    @Test
    public void whenElementRemovedByIndexThenSizeMustBeDecreased() {
        assertEquals(100, carList.size());
        carList.remove(1);
        assertEquals(99, carList.size());
    }

    @Test
    public void whenElementRemovedThenSizeMustBeDecreased() {
        Car car = new Car("car1", 99);
        carList.add(car);
        assertEquals(101, carList.size());
        carList.remove(car);
        assertEquals(100, carList.size());
    }

    @Test
    public void whenNotExistElementRemovedThenRemoveFalse() {
        Car car = new Car("qwerty", 99);
        boolean isRemove = carList.remove(car);
        assertFalse(isRemove);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveOutBoundIndexThenThrowIndexOutOfBoundsException() {
        carList.remove(-10);
    }

    @Test
    public void whenListClearThenSizeMustBe0() {
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test
    public void methodGetReturnedRightValue() {
        Car car = carList.get(0);
        assertEquals("brand0", car.getBrand());
    }

    @Test
    public void insertIntoMiddle() {
        Car car = new Car("BMW", 99);
        carList.add(carList.size() / 2, car);
        Car carFromList = carList.get(carList.size() / 2);
        assertEquals(car, carFromList);
    }

    @Test
    public void insertIntoFirstPosition() {
        Car car = new Car("BMW", 99);
        carList.add(0, car);
        Car carFromList = carList.get(0);
        assertEquals(car, carFromList);
    }

    @Test
    public void insertIntoLastPosition() {
        Car car = new Car("BMW", 99);
        carList.add(100, car);
        Car carFromList = carList.get(100);
        assertEquals(car, carFromList);
    }

    @Test
    public void removeFirstElement() {
        Car car = new Car("тестовый автомобиль", 999);
        Car car1 = carList.get(0);
        carList.add(0, car);
        carList.remove(car);
        Car car2 = carList.get(0);

        assertEquals(car1, car2);
        assertFalse(carList.remove(car));
    }

    @Test
    public void removeLastElement() {
        Car car = new Car("тестовый автомобиль", 999);
        Car car1 = carList.get(carList.size() - 1);
        carList.add(carList.size(), car);
        carList.remove(car);
        Car car2 = carList.get(carList.size() - 1);

        assertEquals(car1, car2);
        assertFalse(carList.remove(car));
    }

    @Test
    public void removeFirstElementByIndex() {
        Car car = new Car("тестовый автомобиль", 999);
        Car car1 = carList.get(0);
        carList.add(0, car);
        carList.remove(0);
        Car car2 = carList.get(0);

        assertEquals(car1, car2);
        assertFalse(carList.remove(car));
    }

    @Test
    public void removeLastElementByIndex() {
        Car car = new Car("тестовый автомобиль", 999);
        Car car1 = carList.get(carList.size() - 1);
        carList.add(carList.size(), car);
        carList.remove(carList.size() - 1);
        Car car2 = carList.get(carList.size() - 1);

        assertEquals(car1, car2);
        assertFalse(carList.remove(car));
    }

    @Test
    public void clearListAndAddElement() {
        carList.clear();
        Car car = new Car("Тестовая", 999);
        carList.add(car);
        assertEquals(1, carList.size());
        assertEquals(car, carList.get(0));
    }
}