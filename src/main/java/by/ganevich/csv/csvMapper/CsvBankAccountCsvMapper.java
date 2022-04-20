package by.ganevich.csv.csvMapper;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import by.ganevich.entity.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class CsvBankAccountCsvMapper extends BaseCsvMapper<BankAccount> {

    @Override
    public String getCsvString(BankAccount bankAccount) {
        return bankAccount.getId().toString() + ","
                + bankAccount.getNumber().toString() + ","
                + bankAccount.getOwner().getId().toString() + ","
                + bankAccount.getBankProducer().getId().toString() + ","
                + bankAccount.getCurrency().toString() + ","
                + bankAccount.getAmountOfMoney().toString();
    }

    public ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(BankAccount.class);
        String[] columns = new String[] {
                "number",
                "currency",
                "amountOfMoney",
                "bankId",
                "clientId"
        };
        strategy.setColumnMapping(columns);
        return strategy;
    }

}
