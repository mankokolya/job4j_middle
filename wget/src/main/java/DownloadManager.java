import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.concurrent.*;

public class DownloadManager {

    private final int downloadLimit;
    private final String file;

    public DownloadManager(String file, String downloadLimit) {
        this.file = file;
        this.downloadLimit = Integer.parseInt(downloadLimit);
    }

    public void download() {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("output.txt")) {
            long bytesRead = 0;
            long pause;
            ExecutorService executor = Executors.newSingleThreadExecutor();
            while (bytesRead != -1) {
                Future<Long[]> future = executor.submit(new DownloadLimit(downloadLimit, inputStream, fileOutputStream));
                Long[] bytesReadAndPause = future.get();
                bytesRead = bytesReadAndPause[0];
                if (bytesRead > 0) {
                    System.out.println(bytesRead + " bytesRead");
                    pause = bytesReadAndPause[1];
                    if (pause > 0) {
                        System.out.println();
                        System.out.println("pause is: " + pause);
                        System.out.println();
                        Thread.sleep(pause);
                    }
                }
            }
            System.out.println("File is downloaded");
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

