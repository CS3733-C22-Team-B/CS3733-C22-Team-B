package edu.wpi.cs3733.c22.teamB.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader2 {

    private File file;
    protected BufferedReader br;
    private List<String> fileList;

    public CSVReader2(File file) throws IOException {
        this.file = file;
        br = new BufferedReader(new FileReader(file));
        this.fileList = bufferedReaderToString();
        br.close();
    }

    private List<String> bufferedReaderToString() throws IOException {
        List<String> fileString = new ArrayList<>();
        String line = br.readLine(); // Header ignored

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            fileString.add(line);
        }

        return fileString;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<String> read() throws IOException {
        List<String> fileList;
        br = new BufferedReader(new FileReader(this.file));
        fileList = bufferedReaderToString();
        br.close();

        return fileList;
    }
}
