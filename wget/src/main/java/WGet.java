public class WGet {

    public static void main(String[] args) {
        DownloadManager downloadManager = new DownloadManager(args[0], args[1]);
        downloadManager.download();
    }
}
