package edu.wpi.cs3733.c22.teamB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private String file;
    protected BufferedReader br;
    private List<String[]> fileList;

    public CSVReader(String file) throws IOException {
        this.file = file;
        br = new BufferedReader(new FileReader(file));
        this.fileList = bufferedReaderToString();
        br.close();
    }

    private List<String[]> bufferedReaderToString() throws IOException {
        List<String[]> fileString = new ArrayList<>();
        String line = br.readLine(); // Header ignored

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(",");
            fileString.add(fields);
        }

        return fileString;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<String[]> readAll() throws IOException {
        List<String[]> fileList;
        br = new BufferedReader(new FileReader(this.file));
        fileList = bufferedReaderToString();
        br.close();

        return fileList;
    }
}
