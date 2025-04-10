package ru.itis.collections;


import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.hashSet.HashSet;

import java.util.Iterator;

import static org.junit.Assert.*;

public class CollectionTest {
    Collection<Integer> collection;
    int size = 10_000_000;

    @Before
    public void setUp() throws Exception {
        collection = new HashSet<>();
        for (int i = 0; i < size; i++) {
            collection.add(i);
        }
    }

    @Test
    public void counterTestForForEach() {
        int index = 0;
        for (Integer i : collection) {
            assertTrue(collection.contains(i));
            index++;
        }
        assertEquals(10_000_000, index);
    }

    @Test
    public void iteratorTest() {
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            assertNotEquals(iterator.next(), iterator.next());
        }
    }

}
