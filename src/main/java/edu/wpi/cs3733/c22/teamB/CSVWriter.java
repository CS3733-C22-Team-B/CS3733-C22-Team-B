package edu.wpi.cs3733.c22.teamB;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSVWriter {

    private String file;
    private PrintWriter writer;

    public CSVWriter(String file) throws FileNotFoundException {
        this.file = file;
        this.writer = new PrintWriter(file);
    }

    public void write(String data) {
        writer.write(data);
        writer.flush();
        writer.close();
    }

    public void setFile(String file) throws FileNotFoundException {
        this.file = file;
        this.writer = new PrintWriter(file);
    }

    public void empty() {
        writer.write("");
        writer.close();
    }
}
