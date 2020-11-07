package ru.job4j.concurrency.threadsafecollection;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.DynamicArray;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this") private DynamicArray<T> container = new DynamicArray<T>(10);

    public synchronized void add(T value) {
        container.add(value);
    }

    public synchronized T get(int index) {
        return container.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.container).iterator();
    }

    private DynamicArray<T> copy(DynamicArray<T> container) {
        DynamicArray<T> copy = new DynamicArray<>();
        container.forEach(copy::add);
        return copy;
    }
}
