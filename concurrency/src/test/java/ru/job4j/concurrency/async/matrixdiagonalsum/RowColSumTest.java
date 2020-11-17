package ru.job4j.concurrency.async.matrixdiagonalsum;

import org.junit.Test;
import ru.job4j.concurrency.async.matrixdiagonalsum.RowColSum.Sums;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RowColSumTest {

    @Test
    public void testSynchronous() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] sums = RowColSum.sum(matrix);
        int firstRowSumExpect = 6;
        int firstColSumExpect = 12;
        assertEquals(firstColSumExpect, sums[0].getColSum());
        assertEquals(firstRowSumExpect, sums[0].getRowSum());
    }

    @Test
    public void testASynchronousRowColSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] sums = RowColSum.asyncSum(matrix);
        int firstRowSumExpect = 6;
        int firstColSumExpect = 12;
        int secondRowSumExpect = 15;
        int secondColSumExpect = 15;
        assertEquals(firstColSumExpect, sums[0].getColSum());
        assertEquals(firstRowSumExpect, sums[0].getRowSum());
        assertEquals(secondRowSumExpect, sums[1].getRowSum());
        assertEquals(secondColSumExpect, sums[1].getColSum());
    }

}