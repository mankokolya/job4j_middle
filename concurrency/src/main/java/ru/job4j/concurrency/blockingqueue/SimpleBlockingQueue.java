package ru.job4j.concurrency.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) {
        while (this.queue.size() == this.limit) {
            try {
                System.out.println("space is full, waiting");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.queue.offer(value);
        if (this.queue.size() == 1) {
            this.notifyAll();
        }
    }

    public synchronized T poll() {
        while (this.queue.size() == 0) {
            try {
                System.out.println("no elements present, waiting for elements");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (this.queue.size() == this.limit) {
            this.notifyAll();
        }
        return this.queue.poll();

    }
}
