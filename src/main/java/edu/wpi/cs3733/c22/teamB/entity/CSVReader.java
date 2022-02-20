package edu.wpi.cs3733.c22.teamB.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private File file;
    protected BufferedReader br;
    private List<String> fileList;

    public CSVReader() { }

    public CSVReader(File file) throws IOException {
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

    public List<String> firstRestore(String fileName) throws IOException {
        List<String> fileList;

        InputStream inputStream = getClass().getResourceAsStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        this.br = new BufferedReader(inputStreamReader);

        fileList = bufferedReaderToString();

        return fileList;
    }
}
