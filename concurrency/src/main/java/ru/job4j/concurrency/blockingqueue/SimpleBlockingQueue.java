package ru.job4j.concurrency.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int limit = 5;

    public SimpleBlockingQueue() {
    }

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }


    public synchronized void offer(T value) throws InterruptedException {
        while (this.queue.size() == this.limit) {
                System.out.println("space is full, waiting");
                this.wait();
        }
        this.queue.offer(value);
        if (this.queue.size() == 1) {
            this.notifyAll();
        }
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.size() == 0) {
                System.out.println("no elements present, waiting for elements");
                this.wait();
        }
        if (this.queue.size() == this.limit) {
            this.notifyAll();
        }
        return this.queue.poll();

    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
