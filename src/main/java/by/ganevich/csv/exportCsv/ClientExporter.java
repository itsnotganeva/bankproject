package by.ganevich.csv.exportCsv;

import by.ganevich.csv.csvMapper.BaseCsvMapper;
import by.ganevich.csv.csvMapper.CsvClientCsvMapper;
import by.ganevich.service.BaseService;
import by.ganevich.service.ClientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ClientExporter extends CsvExporter {

    private final ClientService clientService;
    private final CsvClientCsvMapper clientMapper;

    private final String fileName = "exportClients.csv";

    @Override
    public BaseService getService() {
        return this.clientService;
    }

    @Override
    public BaseCsvMapper getMapper() {
        return this.clientMapper;
    }

}
