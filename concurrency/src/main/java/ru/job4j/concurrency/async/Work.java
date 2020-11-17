package ru.job4j.concurrency.async;

import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Work {

    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("You: I am working");
            TimeUnit.SECONDS.sleep(1);
            count++;
        }
    }

    private static CompletableFuture<Void> throwTrash() {
        return CompletableFuture.runAsync(
                () -> {
                    System.out.println("Son: Mother/Father, I am going to throw the trash");

                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Son: Mom/Dad I am home");
                }
        );
    }

    public static void runAsyncExample() throws Exception {
        CompletableFuture<Void> gtt = throwTrash();
        gtt.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Son: I am washing my hands");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Son: I have already washed my hands");
        });
        iWork();
    }


    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Son: Mom/Dod I am going to the shop");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Son: Mom/Dad I bought " + product);
                    return product;
                }
        );
    }


    public static void supplyAsyncExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Milk");
        iWork();
        System.out.println("Products bought: " + bm.get());
    }

    public static void thenAcceptExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Milk");
        bm.thenAccept((product) -> System.out.println("Son: I put " + product + " in the fridge"));
        iWork();
        System.out.println("Bought: " + bm.get());
    }

    public static void thenApplyExample() throws Exception {
        CompletableFuture<String> bm = buyProduct("Milk")
                .thenApply((product) -> "Son: I poured " + product + " into your cup. Here you go.");
        iWork();
        System.out.println(bm.get());

    }

    public static void thenComposeExample() {
        CompletableFuture<Void> result = buyProduct("Milk").thenCompose(a -> throwTrash());
    }

    public static void thenCombineExample() throws Exception {
        CompletableFuture<String> result = buyProduct("Milk")
                .thenCombine(buyProduct("Bread"), (r1, r2) -> "Bought: " + r1 + " and " + r2);
        iWork();
        System.out.println(result.get());
    }

    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " is washing his/her hands");
        });
    }

    public static void allOfExample() throws Exception {
        CompletableFuture<Void> all = CompletableFuture.allOf(
                washHands("Father"), washHands("Mother"),
                washHands("Ivan"), washHands("Borya")
        );
        TimeUnit.SECONDS.sleep(3);
    }

    public static CompletableFuture<String> whoWashingHands(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + ", is washing his/her hands";
        });
    }

    public static void anyOfExample() throws Exception {
        CompletableFuture<Object> first = CompletableFuture.anyOf(
                washHands("Father"), washHands("Mother"),
                washHands("Ivan"), washHands("Borya")
        );
        System.out.println("Who is washing his/her hands?");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(first.get());
    }

    public static void main(String[] args) throws Exception {
        Work.anyOfExample();
    }

}
