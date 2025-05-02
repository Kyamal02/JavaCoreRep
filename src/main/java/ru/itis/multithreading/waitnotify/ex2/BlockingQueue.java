package ru.itis.multithreading.waitnotify.ex2;


import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private final Queue<Runnable> queue = new LinkedList<>();


    public synchronized void add(Runnable runnable) {
        queue.add(runnable);
        notifyAll();

        //так получать будет только один поток, так как не notifyAll();
//        if(queue.size() == 10) {
//            notify();
//        }
    }

    public synchronized Runnable take() {
        if (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return queue.poll();
    }
}
