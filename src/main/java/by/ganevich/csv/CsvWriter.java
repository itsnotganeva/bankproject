package by.ganevich.csv;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public void writeCsv(String fileName, String input) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, true));
        writer.writeNext(input.split(","));
        writer.close();
    }
}
