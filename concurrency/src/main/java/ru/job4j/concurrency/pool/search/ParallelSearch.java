package ru.job4j.concurrency.pool.search;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private T[] container = null;
    private T object = null;

    public ParallelSearch() {
    }

    private ParallelSearch(T[] container, T object) {
        this.container = container;
        this.object = object;
    }

    @Override
    protected Integer compute() {
        if (container.length <= 10) {
            return new LinearSearch<>(container).searchIndex(object);
        }
        int mid = container.length / 2;
        var leftSearch = new ParallelSearch<>(Arrays.copyOfRange(container, 0, mid), object);
        var rightSearch = new ParallelSearch<>(Arrays.copyOfRange(container, mid +1, container.length - 1), object);
        leftSearch.fork();
        rightSearch.fork();
        Integer leftResult = leftSearch.join();
        Integer rightResult = rightSearch.join();
        return leftResult > 0 ? leftResult : rightResult;
    }

    public int search(T[] container, T object) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new ParallelSearch<>(container, object));
    }
}
