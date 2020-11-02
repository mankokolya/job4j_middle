import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;

public class DownloadManager implements Callable<Integer> {

    private String fileUrl;
    private int downloadLimit;

    DownloadManager(String fileUrl, int downloadLimit) {
        this.fileUrl = fileUrl;
        this.downloadLimit = downloadLimit;
    }

    public void download () {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(fileUrl).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream("output.txt")) {
            byte[] dataBuffer = new byte[downloadLimit];
            int bytesRead;
            startTime = System.currentTimeMillis();
            while (bytesDownloaded <= downloadLimit
                    && (bytesRead = inputStream.read(dataBuffer, 0, downloadLimit)) != -1) {
                System.out.println(bytesRead + " bytesRead");
                bytesDownloaded += bytesRead;
                fileOutputStream.write(dataBuffer, 0, downloadLimit);
                pauseDownload(downloadLimit);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pauseDownload(int downloadLimit) throws InterruptedException {
        if (bytesDownloaded >= downloadLimit) {
            long usedTime = (System.currentTimeMillis() - startTime);
            System.out.println(usedTime + " used Time in milliseconds");
            long pauseTime = MILLISECONDS_IN_SECOND - usedTime;
            if (pauseTime > 0) {
                System.out.println(pauseTime + " pause");
                Thread.sleep(pauseTime);
            }
            bytesDownloaded = 0;
            startTime = System.currentTimeMillis();
        }
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
