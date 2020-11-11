package ru.job4j.concurrency;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.concurrency.blockingqueue.SimpleBlockingQueue;

@ThreadSafe
public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final int stopConsuming = -1;

        final Thread consumer = new Thread(
                () -> {
                    boolean working = true;
                    while (working) {
                        int result = queue.poll();
                        if (result != stopConsuming) {
                            System.out.println(result);
                        } else {
                            working = false;
                        }
                    }
                }
        );
        consumer.start();

        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(stopConsuming);
                }
        );
        producer.start();
    }
}
