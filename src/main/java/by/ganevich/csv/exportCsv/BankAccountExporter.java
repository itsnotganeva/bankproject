package by.ganevich.csv.exportCsv;

import by.ganevich.csv.csvMapper.BaseCsvMapper;
import by.ganevich.csv.csvMapper.CsvBankAccountCsvMapper;
import by.ganevich.service.BankAccountService;
import by.ganevich.service.BaseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class BankAccountExporter extends CsvExporter {

    private final BankAccountService bankAccountService;
    private final CsvBankAccountCsvMapper bankAccountMapper;

    private final String fileName = "exportBankAccounts.csv";

    @Override
    public BaseService getService() {
        return this.bankAccountService;
    }

    @Override
    public BaseCsvMapper getMapper() {
        return this.bankAccountMapper;
    }
}
