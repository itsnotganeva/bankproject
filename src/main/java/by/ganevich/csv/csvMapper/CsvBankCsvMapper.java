package by.ganevich.csv.csvMapper;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import by.ganevich.entity.Bank;
import org.springframework.stereotype.Component;

@Component
public class CsvBankCsvMapper extends BaseCsvMapper<Bank> {

    @Override
    public String getCsvString(Bank bank) {
        return bank.getId().toString() + "," + bank.getName();
    }

    @Override
    public ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Bank.class);
        String[] columns = new String[] {"bankName"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
