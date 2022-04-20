package by.ganevich.csv.csvMapper;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import by.ganevich.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class CsvClientCsvMapper extends BaseCsvMapper<Client> {

    @Override
    public String getCsvString(Client client) {
        return client.getId().toString() + "," + client.getName()
                + "," + client.getType().toString();
    }

    @Override
    public ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Client.class);
        String[] columns = new String[] {"name", "type"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
