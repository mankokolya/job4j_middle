package ru.job4j.concurrency.training.dataloader;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DataSourceLoader dataSourceLoader = new DataSourceLoader();
        Thread thread1 = new Thread(dataSourceLoader, "DataSourceLoader");
        NetworkConnectionsLoader networkConnectionsLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(networkConnectionsLoader, "NetworkConnection");
        thread1.start();
        thread2.start();
        try{
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
