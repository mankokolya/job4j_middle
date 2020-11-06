import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;

public class DownloadLimit implements Callable<Long[]> {
    private final static long MILLISECONDS_IN_SECOND = 1000;
    private final int speedLimit;
    private final FileOutputStream fileOutputStream;
    private final BufferedInputStream inputStream;

    public DownloadLimit(int limit, BufferedInputStream inputStream, FileOutputStream fileOutputStream) {
        this.speedLimit = limit;
        this.fileOutputStream = fileOutputStream;
        this.inputStream = inputStream;
    }

    @Override
    public Long[] call() throws Exception {
        long startTime = System.currentTimeMillis();
        long pause = 0;
        byte[] dataBuffer = new byte[speedLimit];
        int bytesRead = inputStream.read(dataBuffer, 0, speedLimit);
        if (bytesRead != -1) {
            fileOutputStream.write(dataBuffer, 0, bytesRead);
            pause = calculatePause(startTime);
        }
        return new Long[]{(long) bytesRead, pause};
    }

    private long calculatePause(long startTime) {
        long usedTime = (System.currentTimeMillis() - startTime);
        System.out.println(usedTime + " used Time in milliseconds");
        return MILLISECONDS_IN_SECOND - usedTime;
    }
}
