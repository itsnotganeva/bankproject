package by.ganevich.csv.importCsv;

import by.ganevich.csv.csvMapper.BaseCsvMapper;
import by.ganevich.service.BaseService;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class CsvImporter<T> {

    public abstract String getFileName();

    public abstract BaseService getService();

    public abstract BaseCsvMapper getMapper();

    public void importCsv() throws IOException {
        log.info("Import csv is called");
        doImport(getFileName());
        File file = new File(getFileName());
        log.info("File " + file.getName() + "is successfully imported");
        file.delete();
    }

    public void doImport(String fileName) throws IOException {
        List<T> entities = getMapper()
                .toEntity(fileName);
        for (T entity : entities) {
            getService().save(entity);
        }
    }

}
