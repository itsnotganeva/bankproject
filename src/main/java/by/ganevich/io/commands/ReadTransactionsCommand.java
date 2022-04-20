package by.ganevich.io.commands;

import by.ganevich.dto.ClientDto;
import by.ganevich.dto.TransactionToViewDto;
import by.ganevich.entity.Client;
import by.ganevich.entity.Transaction;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.mapper.interfaces.ClientMapper;
import by.ganevich.service.ClientService;
import by.ganevich.service.TransactionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.sql.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class ReadTransactionsCommand extends BaseCommand {

    private final String commandName = "readTransactions";

    private final TransactionService transactionService;
    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Valid
    private TransactionToViewDto transactionToViewDto;

    @Override
    public String getDescriptionValue() {
        String description = "readTransactions type=sent/received clientName=? "
                + "dateBefore=YYYY-MM-DD dateAfter=YYYY-MM-DD";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Read transactions command is called");

        CommandResult commandResult = new CommandResult();
        Set<Transaction> transactions = new HashSet<>();

        Client client = clientMapper.toEntity(transactionToViewDto.getClient());
        Date dateBefore = Date.valueOf(transactionToViewDto.getDateBefore());
        Date dateAfter = Date.valueOf(transactionToViewDto.getDateAfter());

        if (transactionToViewDto.getType().equals("sent")) {
            transactions =
                    transactionService.readAllByDateAndSender(dateBefore, dateAfter, client);
        } else if (transactionToViewDto.getType().equals("received")) {
            transactions =
                    transactionService.readAllByDateAndReceiver(dateBefore, dateAfter, client);
        }

        log.info("Read transactions command is complete");

        commandResult.setResult(transactions);
        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        TransactionToViewDto transactionToViewDto = new TransactionToViewDto();
        ClientDto clientDto = clientMapper
                .toDto(clientService.findClientByName(commandDescriptor.getParameters().get("clientName")));

        transactionToViewDto.setClient(clientDto);
        transactionToViewDto.setType(commandDescriptor.getParameters().get("type"));
        transactionToViewDto.setDateBefore(commandDescriptor.getParameters().get("dateBefore"));
        transactionToViewDto.setDateAfter(commandDescriptor.getParameters().get("dateAfter"));

        this.transactionToViewDto = transactionToViewDto;
        return this;
    }
}
