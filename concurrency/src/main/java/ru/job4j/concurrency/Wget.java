package ru.job4j.concurrency;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int index = 0; index <= 100; index++) {
                            System.out.print("\rLoading: " + index + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
    }
}
