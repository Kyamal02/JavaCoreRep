package ru.itis.collections;

import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.arrayList.ArrayList;
import ru.itis.collections.linkedList.LinkedList;


import java.util.Iterator;

import static org.junit.Assert.*;

public class ListTest {

    List<Car> carList;

    public <T> List<T> listFactory(T type) {
        return new
                LinkedList<>();
    }

    @Before
    public void setUp() {
        carList = listFactory(new Car());

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

    @Test
    public void addElementAndContainsReturnTrueForThisElement() {
        Car car = new Car("testBrand", 999);
        carList.add(car);
        assertTrue(carList.contains(new Car("testBrand", 999)));

        carList.remove(car);
        assertFalse(carList.contains(new Car("testBrand", 999)));
    }

    @Test
    public void addNullElementAndContainsReturnTrueForNull() {
        assertTrue(carList.add(null));
        assertTrue(carList.contains(null));

        assertTrue(carList.remove(null));
        assertFalse(carList.contains(null));
    }

    @Test
    public void addIntoNewListElementAndRemoveIt() {

        carList = listFactory(new Car());
        assertTrue(carList.add(null));

        assertTrue(carList.contains(null));

        assertTrue(carList.remove(null));
        assertFalse(carList.remove(null));

        assertFalse(carList.contains(null));

        for (int i = 0; i < 5; i++) {
            carList.add(new Car("Brand" + i, i));
        }

        carList = listFactory(new Car());
        assertTrue(carList.add(null));

        assertTrue(carList.contains(null));

        assertTrue(carList.remove(null));
        assertFalse(carList.remove(null));

        assertFalse(carList.contains(null));
    }

    @Test
    public void forEachTest() {
        int index = 0;
        for (Car car : carList) {
            assertTrue(car.getNumber().equals(index));
            index++;
        }

        assertEquals(100, index);
    }

    @Test
    public void iteratorTest() {
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            assertNotEquals(iterator.next(), iterator.next());
        }
    }
}