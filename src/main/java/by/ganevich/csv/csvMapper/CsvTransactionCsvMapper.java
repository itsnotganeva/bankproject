package by.ganevich.csv.csvMapper;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import by.ganevich.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class CsvTransactionCsvMapper extends BaseCsvMapper<Transaction> {

    @Override
    public String getCsvString(Transaction transaction) {
        return transaction.getId().toString() + ","
                + transaction.getSender().getId().toString() + ","
                + transaction.getReceiver().getId().toString() + ","
                + transaction.getAmountOfMoney().toString() + ","
                + transaction.getDate().toString() + ",";
    }

    @Override
    public ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Transaction.class);
        String[] columns = new String[] {
                "senderId",
                "receiverId",
                "amountOfMoney",
                "senderAccountId",
                "receiverAccountId",
                "date"
        };
        strategy.setColumnMapping(columns);
        return strategy;
    }

}
