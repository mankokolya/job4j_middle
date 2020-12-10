package ru.job4j.concurrency.pool.mergesort;

public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int from, int to) {
        if (from == to) {
            return new int[]{array[from]};
        }
        int mid = (from + to) / 2;
        return merge(
                sort(array, from, mid),
                sort(array, mid + 1, to)
        );
    }

    public static int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        int[] result = new int[left.length + right.length];
        while (resultIndex != result.length) {
            if (leftIndex == left.length) {
                result[resultIndex++] = right[rightIndex++];
            } else if (rightIndex == right.length) {
                result[resultIndex++] = left[leftIndex++];
            } else if (left[leftIndex] < right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        return result;
    }
}
