package ru.job4j.concurrency.blockingqueue;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;


public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        AtomicInteger result = new AtomicInteger();
        Thread producer = new Thread(
                () -> queue.offer(5)
        );

        Thread consumer = new Thread(
                () -> result.set(queue.poll())
        );
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
        assertEquals(5, result.get());

    }
}