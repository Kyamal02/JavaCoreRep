package ru.itis.collections;

import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.hashSet.HashSet;

import static org.junit.Assert.*;

public class HashSetTest {

    Set<Integer> integerSet = new HashSet<>();

    @Before
    public void setUp() {
        integerSet = new HashSet<>();
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
}