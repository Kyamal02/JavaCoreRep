package ru.itis.collections;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ru.itis.collections.linkedList.LinkedList;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue<Car> queue;

    @Before
    public void setUp() throws Exception {
        queue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            queue.add(new Car("brand" + i, i));
        }
    }

    @Test
    public void testAdd() {

        assertEquals(10, queue.size());
    }

    @Test
    public void testPeak() {
        Car car = queue.peek();

        assertEquals(car, new Car("brand0", 0));
        assertEquals(10, queue.size());
    }

    @Test
    public void testPoll() {
        Car car = queue.poll();

        assertEquals(car, new Car("brand0", 0));
        assertEquals(9, queue.size());
    }

    @Test
    public void peakWhenQueueSize0() {
        queue.clear();
        assertEquals(0, queue.size());
        Car peak = queue.peek();
        assertNull(peak);
    }

    @Test
    public void pollWhenQueueSize0() {
        queue.clear();
        assertEquals(0, queue.size());
        Car peak = queue.poll();
        assertNull(peak);
    }
}