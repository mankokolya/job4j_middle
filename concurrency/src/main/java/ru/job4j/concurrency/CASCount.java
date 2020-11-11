package ru.job4j.concurrency;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int temp;
        do {
            temp = count.get();
        } while (!count.compareAndSet(temp, ++temp));
    }

    public int get() {
        return count.get();
    }
}
