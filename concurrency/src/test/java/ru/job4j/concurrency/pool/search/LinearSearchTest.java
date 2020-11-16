package ru.job4j.concurrency.pool.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearSearchTest {

    @Test
    public void testLinearSearch() {
        Integer[] ints = {4, 5, 3, 7, 9};
        LinearSearch<Integer> search = new LinearSearch<>(ints);
        int index = search.searchIndex(5);
        assertEquals(index, 1);
    }

    @Test
    public void parallelLinearSearch() {
        Integer[] ints = {4, 5, 3, 7, 9, 1, 11, 45, 223, 23, 123, 2};
        ParallelSearch<Integer> search = new ParallelSearch<>();
        int index = search.search(ints, 5);
        assertEquals(index, 1);
    }
 }