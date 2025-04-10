package ru.itis.collections;

import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.hashSet.HashSet;
import ru.itis.collections.hashSet.NewHashSet;


import static org.junit.Assert.*;

public class HashSetTest {

    Set<Integer> integerSet;

    @Before
    public void setUp() {
        integerSet = new NewHashSet<>();
        for (int i = 0; i < 10_000_000; i++) {
            integerSet.add(i);
        }
    }

    @Test
    public void whenAdd3SimilarObjectsThenSizeIncreaseBy1() {
        assertEquals(getExpected(), integerSet.size());

        Integer number = getExpected() + 1;
        assertTrue(integerSet.add(number));
        assertFalse(integerSet.add(number));
        assertFalse(integerSet.add(number));

        assertEquals(10_000_001, integerSet.size());
    }

    private static int getExpected() {
        return 10_000_000;
    }

    @Test
    public void whenSetClearThenSize0() {
        integerSet.clear();
        assertEquals(0, integerSet.size());
    }

    @Test
    public void whenElementRemoveThenSizeDecrease() {
        assertTrue(integerSet.remove(30));
        assertEquals(9_999_999, integerSet.size());
        assertFalse(integerSet.remove(30));
        assertEquals(9_999_999, integerSet.size());
    }

    @Test
    public void add2NullElement() {
        assertTrue(integerSet.add(null));
        assertEquals(10_000_001, integerSet.size());
        assertFalse(integerSet.add(null));
        assertEquals(10_000_001, integerSet.size());
    }

    @Test
    public void add2NullElementAndRemove2NullElement() {
        assertTrue(integerSet.add(null));
        assertEquals(10_000_001, integerSet.size());
        assertFalse(integerSet.add(null));
        assertEquals(10_000_001, integerSet.size());

        assertTrue(integerSet.remove(null));
        assertFalse(integerSet.remove(null));
        assertEquals(10_000_000, integerSet.size());
    }

    @Test
    public void add2NullElementWithIntegerMaxMinAndRemove2NullElementAnd2MaxMinInteger() {
        assertTrue(integerSet.add(null));
        assertEquals(10_000_001, integerSet.size());
        assertFalse(integerSet.add(null));
        assertEquals(10_000_001, integerSet.size());
        assertTrue(integerSet.add(Integer.MIN_VALUE));
        assertFalse(integerSet.add(Integer.MIN_VALUE));
        assertEquals(10_000_002, integerSet.size());


        assertTrue(integerSet.remove(null));
        assertFalse(integerSet.remove(null));
        assertEquals(10_000_001, integerSet.size());

        assertTrue(integerSet.remove(Integer.MIN_VALUE));
        assertFalse(integerSet.remove(Integer.MIN_VALUE));
        assertEquals(10_000_000, integerSet.size());
    }

    @Test
    public void addElementAndReturnContainsTrueForThisElement() {
        Integer integer = new Integer(10_100_100);
        assertTrue(integerSet.add(integer));
        assertTrue(integerSet.contains(new Integer(10_100_100)));

        assertTrue(integerSet.remove(integer));
        assertFalse(integerSet.contains(new Integer(10_100_100)));
    }

    @Test
    public void addNullElementAndCheckContainsAndRemove() {
        // Добавляем null и проверяем наличие
        assertTrue(integerSet.add(null));
        assertTrue(integerSet.contains(null));

        // Удаляем null и проверяем отсутствие
        assertTrue(integerSet.remove(null));
        assertFalse(integerSet.contains(null));
    }

    @Test
    public void addAndRemoveElementsInNewSet() {
        // Создаем новый пустой HashSet
        integerSet = new HashSet<>();

        // Добавляем null и проверяем
        assertTrue(integerSet.add(null));
        assertTrue(integerSet.contains(null));

        // Удаляем null и проверяем
        assertTrue(integerSet.remove(null));
        assertFalse(integerSet.remove(null));
        assertFalse(integerSet.contains(null));

        // Добавляем несколько элементов
        for (int i = 0; i < 5; i++) {
            assertTrue(integerSet.add(i));
        }

        // Снова создаем новый HashSet
        integerSet = new HashSet<>();

        // Повторяем проверки с null
        assertTrue(integerSet.add(null));
        assertTrue(integerSet.contains(null));

        assertTrue(integerSet.remove(null));
        assertFalse(integerSet.remove(null));
        assertFalse(integerSet.contains(null));
    }

    @Test
    public void forEachTest() {
        int index = 0;
        for (Integer i : integerSet) {
            assertTrue(integerSet.contains(i));
            index++;
        }
        assertEquals(10_000_000, index);
    }

    @Test
    public void testGrowHashTableWithCollisions() {
        // Внутренний класс для создания элементов с коллизиями
        class CollisionObject {
            private int value;

            public CollisionObject(int value) {
                this.value = value;
            }

            @Override
            public int hashCode() {
                return 42; // Фиксированный хэш-код для всех объектов
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                CollisionObject that = (CollisionObject) obj;
                return value == that.value;
            }
        }

        // Создаём новый пустой HashSet
        Set<CollisionObject> set = new HashSet<>();

        // Добавляем 20 элементов — достаточно для вызова grow при начальной ёмкости 16 и load factor 0.75
        int initialElements = 20;
        for (int i = 0; i < initialElements; i++) {
            assertTrue(set.add(new CollisionObject(i)));
        }

        // Проверяем, что все элементы добавлены
        assertEquals(initialElements, set.size());

        // Проверяем наличие всех элементов после увеличения таблицы
        for (int i = 0; i < initialElements; i++) {
            assertTrue(set.contains(new CollisionObject(i)));
        }

        // Пытаемся добавить дубликат
        assertFalse(set.add(new CollisionObject(0)));
        assertEquals(initialElements, set.size());

        // Удаляем первые 5 элементов
        for (int i = 0; i < 5; i++) {
            assertTrue(set.remove(new CollisionObject(i)));
        }

        // Проверяем, что удалённые элементы отсутствуют
        for (int i = 0; i < 5; i++) {
            assertFalse(set.contains(new CollisionObject(i)));
        }

        // Проверяем, что оставшиеся элементы на месте
        for (int i = 5; i < initialElements; i++) {
            assertTrue(set.contains(new CollisionObject(i)));
        }

        // Проверяем итоговый размер
        assertEquals(initialElements - 5, set.size());
    }

}