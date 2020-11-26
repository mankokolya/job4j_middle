package ru.job4j.concurrency.training.threadfactory;

public class Main {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();
        Thread thread;
        System.out.println("Starting the Threads\n");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.println("Factory stats:\n");
        System.out.printf("%s\n", factory.getStats());
    }

}
