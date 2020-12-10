package ru.job4j.concurrency;

public class ConsoleProgress implements Runnable {

    private final String[] process = {"\\", "|", "/"};
    int runsCount = 0;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            System.out.print("\r load: " + process[runsCount++]);
            if (runsCount >= process.length) {
                runsCount = 0;
            }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
