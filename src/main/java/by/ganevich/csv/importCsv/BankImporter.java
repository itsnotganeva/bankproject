package by.ganevich.csv.importCsv;

import by.ganevich.csv.csvMapper.BaseCsvMapper;
import by.ganevich.csv.csvMapper.CsvBankCsvMapper;
import by.ganevich.service.BankService;
import by.ganevich.service.BaseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class BankImporter extends CsvImporter {

    private final String fileName = "importBanks.csv";

    private final CsvBankCsvMapper bankMapper;
    private final BankService bankService;

    @Override
    public BaseService getService() {
        return this.bankService;
    }

    @Override
    public BaseCsvMapper getMapper() {
        return this.bankMapper;
    }
}
