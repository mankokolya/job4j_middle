package ru.job4j.concurrency;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ParseFile {
    private final File file;

    ParseFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        return Files.readString(file.toPath());
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return Files.readString(file.toPath(), StandardCharsets.US_ASCII);
    }

    public synchronized void saveContent(String content) throws IOException {
        Files.write(file.toPath(), content.getBytes());
    }
}
