package ru.job4j.concurrency.pool.search;

public class LinearSearch<T> {
    private final T[] container;

    public LinearSearch(T[] container) {
        this.container = container;
    }

    public int searchIndex(T object) {
        int result = -1;
        for (int index = 0; index < container.length; index++) {
            if (container[index].equals(object)) {
                result = index;
                break;
            }
        }
        return result;
    }
}
