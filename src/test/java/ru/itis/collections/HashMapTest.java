package ru.itis.collections;

import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.hashMap.HashMap;


import static org.junit.Assert.*;

public class HashMapTest {
    Map<String, Car> carHashMap;
    static int size = 1_000_000;

    @Before
    public void setUp() throws Exception {
        carHashMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            carHashMap.put("Number: " + i, new Car("brand" + i, i));
        }
    }

    // Существующие тесты
    @Test
    public void sizeTest() {
        assertEquals(size, carHashMap.size());
    }

    @Test
    public void putValueByKeyAndSizeIncrease() {
        carHashMap.put("testString", null);
        assertEquals(size + 1, carHashMap.size());
    }

    @Test
    public void put2DifferentValueWithSameKey() {
        Car testCar = new Car("testBrand", 999);
        assertNull(carHashMap.put("testString", testCar));
        Car testCar2 = new Car("testBrand2", 888);
        assertEquals(testCar, carHashMap.put("testString", testCar2));
        assertEquals(testCar2, carHashMap.get("testString"));
        assertEquals(size + 1, carHashMap.size());
    }


    @Test
    public void whenPut100EValuesWith10DifferentKeys() {
        int a;
        for (int i = 0; i < 100; i++) {
            a = i % 10;
            carHashMap.put("brand" + a, new Car("testBrand" + a, 238974));
        }
        assertEquals(size + 10, carHashMap.size());
    }

    @Test
    public void removeByKeyTest() {
        String key = "Number: 500000";
        Car removedCar = carHashMap.remove(key);
        assertEquals(new Car("brand500000", 500000), removedCar); // Предполагается, что equals() в Car работает
        assertEquals(size - 1, carHashMap.size());
        assertNull(carHashMap.get(key)); // Проверка, что ключ удалён
    }

    @Test
    public void removeNonExistentKeyTest() {
        assertNull(carHashMap.remove("NonExistentKey"));
        assertEquals(size, carHashMap.size()); // Размер не изменился
    }

    @Test
    public void putNullKeyTest() {
        Car testCar = new Car("nullBrand", 0);
        carHashMap.put(null, testCar);
        assertEquals(size + 1, carHashMap.size());
        assertEquals(testCar, carHashMap.get(null)); // Проверка, что null-ключ работает
    }


    @Test
    public void replaceExistingKeyTest() {
        String key = "Number: 100";
        Car oldCar = carHashMap.get(key);
        Car newCar = new Car("newBrand", 1000);
        carHashMap.put(key, newCar);
        assertEquals(newCar, carHashMap.get(key));
        assertEquals(size, carHashMap.size()); // Размер не увеличивается, так как ключ уже был
    }


    @Test
    public void collisionTest() {
        // Предполагаем, что Car имеет фиксированный hashCode для демонстрации коллизии
        Map<Car, String> collisionMap = new HashMap<>();
        Car car1 = new Car("brand", 1) {
            @Override
            public int hashCode() {
                return 42;
            }
        };
        Car car2 = new Car("brand", 2) {
            @Override
            public int hashCode() {
                return 42;
            }
        };
        collisionMap.put(car1, "Value1");
        collisionMap.put(car2, "Value2");
        assertEquals(2, collisionMap.size());
        assertEquals("Value1", collisionMap.get(car1));
        assertEquals("Value2", collisionMap.get(car2));
    }
}