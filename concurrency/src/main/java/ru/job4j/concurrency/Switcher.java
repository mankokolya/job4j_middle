package ru.job4j.concurrency;

public class Switcher {

    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();

        Thread first = new Thread(
                () -> {
                    while (true) {
                        barrier.tryMaster();
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.doneMaster();
                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    while (true) {
                        barrier.trySlave();
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.doneSlave();
                    }
                }
        );

        first.start();
        second.start();
        first.join();
        second.join();
    }
}
