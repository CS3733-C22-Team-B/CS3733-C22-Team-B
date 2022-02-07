package edu.wpi.cs3733.c22.teamB.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVWriter2 {

    private PrintWriter writer;
    private File file;

    public CSVWriter2(String fileName) throws FileNotFoundException {
        backupDir(fileName);
    }

    public void write(String data) {
        writer.write(data);
        writer.flush();
        writer.close();
    }

    public void writeAll(List<String> data) {
        String dataAll = "";
        for (String str : data) {
            dataAll = dataAll + str + "\n";
        }
        this.write(dataAll);
    }

    public void setFile(File file) throws FileNotFoundException {
        this.writer = new PrintWriter(file);
    }

    public void empty() {
        writer.write("");
        writer.close();
    }

    private void backupDir(String fileName) throws FileNotFoundException {
        String pathString = new File("").getAbsolutePath();
        File f = new File(pathString);

        File dir = new File(f.getAbsolutePath() + "/backup1");

        File filePath;

        if (!(dir.exists())) {
            dir.getParentFile().mkdirs();

            filePath = new File(dir.getAbsolutePath() + "/" + fileName + ".csv");
            filePath.getParentFile().mkdirs();
        } else {
            filePath = new File(dir.getAbsolutePath() + "/" + fileName + ".csv");
            filePath.getParentFile().mkdirs();
        }

        setFile(filePath);
    }
}
