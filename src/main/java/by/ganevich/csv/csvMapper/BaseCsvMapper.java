package by.ganevich.csv.csvMapper;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import by.ganevich.csv.CsvWriter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCsvMapper<T> {

    public List<T> toEntity(String fileName) throws IOException {
        CsvToBean csv = new CsvToBean();

        CSVReader csvReader = new CSVReader(new FileReader(fileName));

        List<T> entities = new ArrayList<>();
        List list = csv.parse(setColumnMapping(), csvReader);
        for (Object object : list) {
            T entity = (T) object;
            entities.add(entity);
        }
        csvReader.close();
        return entities;
    }


    public void toCsv(String fileName, T entity) throws IOException {
        String toCsv = getCsvString(entity);
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.writeCsv(fileName, toCsv);
    }

    public abstract String getCsvString(T entity);

    public abstract ColumnPositionMappingStrategy setColumnMapping();
}
