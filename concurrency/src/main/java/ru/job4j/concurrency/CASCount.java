package ru.job4j.concurrency;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(int value) {
        this. count = new AtomicReference<>(value);
    }
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
