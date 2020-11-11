package ru.job4j.concurrency.blockingqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class SimpleBlockingQueueTest {

    @Test
    public void fetchListOfString() throws InterruptedException {
        final CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>();
        final List<String> example = List.of("Nick", "Ira", "Bill", "John");

        Thread producer = new Thread(
                () -> example.forEach(queue::offer)
        );

        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(example));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }


    @Test
    public void test2() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        AtomicInteger result = new AtomicInteger();
        Thread producer = new Thread(
                () -> queue.offer(5)
        );

        Thread consumer = new Thread(
                () -> {
                    try {
                        result.set(queue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        consumer.start();
        producer.start();
        producer.join();

        assertEquals(5, result.get());

    }
}