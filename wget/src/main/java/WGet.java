import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WGet {
//    private static final int MILLISECONDS_IN_SECOND = 1000;
//    private static long bytesDownloaded = 0;
//    private static long startTime;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        DownloadManager manager = new DownloadManager(args[0], Integer.parseInt(args[1]));

    }
}
