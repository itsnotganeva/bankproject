package by.ganevich.csv.exportCsv;

import by.ganevich.csv.csvMapper.BaseCsvMapper;
import by.ganevich.service.BaseService;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class CsvExporter<T> {

    public abstract String getFileName();

    public abstract BaseService getService();

    public abstract BaseCsvMapper getMapper();

    public File exportCsv() throws IOException {
        log.info("Export csv is called");
        File file = new File(getFileName());
        file.delete();
        doExport(getFileName());
        File exportedFile = new File(getFileName());
        log.info("File " + exportedFile.getName() + "is successfully exported");
        return exportedFile;
    }

    public void doExport(String fileName) throws IOException {
        List<T> entities = getService().readAll();
        for (T entity : entities) {
            getMapper().toCsv(fileName, entity);
        }
    }
}
