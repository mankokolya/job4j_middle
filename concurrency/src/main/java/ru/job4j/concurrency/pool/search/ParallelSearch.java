package ru.job4j.concurrency.pool.search;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private T[] container = null;
    private T object = null;
    private int from;
    private int to;

    public ParallelSearch() {
    }

    private ParallelSearch(T[] container, T object, int from, int to) {
        this.container = container;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return new LinearSearch<>(container).searchIndex(object);
        }
        int mid = (from + to) / 2;
        var leftSearch = new ParallelSearch<T>(container, object, from, mid);
        var rightSearch = new ParallelSearch<T>(container, object, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        Integer leftResult = leftSearch.join();
        Integer rightResult = rightSearch.join();
        return leftResult > 0 ? leftResult : rightResult;
    }

    public int search(T[] container, T object) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new ParallelSearch<>(container, object, 0, container.length - 1));
    }
}
