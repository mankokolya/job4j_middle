import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WGet {
    private static final int BYTES_IN_KB = 1024;
    private static final int MILLISECONDS_IN_SECOND = 1000;

    public static void main(String[] args) {
        long bytesDownloaded = 0;
        String fileUrl = args[0];
        System.out.println(fileUrl);
        int downloadLimit = Integer.parseInt(args[1]) * 1024;
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("output.txt")) {
            byte[] dataBuffer = new byte[1024];
            System.out.println(dataBuffer.length);
            int bytesRead;
            long startTime = System.currentTimeMillis();
            while (bytesDownloaded <= downloadLimit
                    && (bytesRead = inputStream.read(dataBuffer, 0, 1024)) != -1) {
                System.out.println(bytesRead);
                bytesDownloaded += bytesRead;
                fileOutputStream.write(dataBuffer, 0, 1024);

                if (bytesDownloaded >= downloadLimit) {
                    long usedTime = (System.currentTimeMillis() - startTime);
                    System.out.println(usedTime + " used Time millisec");
                    long pauseTime = MILLISECONDS_IN_SECOND - usedTime;
                    if (pauseTime > 0) {
                        System.out.println(pauseTime + " pause");
                        Thread.sleep(pauseTime);
                        bytesDownloaded = 0;
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws Exception {
//        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
//        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
//             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
//            byte[] dataBuffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//                fileOutputStream.write(dataBuffer, 0, bytesRead);
//                Thread.sleep(1000);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
