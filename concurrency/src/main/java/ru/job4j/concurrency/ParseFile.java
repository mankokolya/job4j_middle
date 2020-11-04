package ru.job4j.concurrency;

import java.io.*;

public class ParseFile {
    private final File file;

    ParseFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        InputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            output += (char) data;
        }
        return output;
    }

    public String getContentWithoutUnicode() throws IOException {
        InputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
        }
        return output;
    }

    public void saveContent(String content) throws IOException {
        OutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
