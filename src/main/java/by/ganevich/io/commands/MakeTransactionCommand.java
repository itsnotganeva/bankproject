package by.ganevich.io.commands;

import by.ganevich.dto.ConductTransactionDto;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.service.TransactionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class MakeTransactionCommand extends BaseCommand {

    private final String commandName = "makeTransaction";

    private final TransactionService transactionService;

    @Valid
    private ConductTransactionDto conductTransactionDto;

    @Override
    public String getDescriptionValue() {
        String description = "makeTransaction senderAccountNumber=? "
                + "receiverAccountNumber=? amountOfMoney=?";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Make transaction command is called");

        Integer senderAccountNumber = Integer.parseInt(conductTransactionDto.getSenderAccountNumber());

        Integer receiverAccountNumber = Integer.parseInt(conductTransactionDto.getReceiverAccountNumber());

        Double sum = Double.valueOf(conductTransactionDto.getAmountOfMoney());

        transactionService.sendMoney(senderAccountNumber, receiverAccountNumber, sum);

        CommandResult commandResult = new CommandResult();
        String result = "Transaction completed successfully!";
        commandResult.setResult(result);

        log.info("Make transaction command is complete");

        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        ConductTransactionDto conductTransactionDto = new ConductTransactionDto();
        conductTransactionDto
                .setSenderAccountNumber(commandDescriptor.getParameters().get("senderAccountNumber"));

        conductTransactionDto
                .setReceiverAccountNumber(commandDescriptor.getParameters().get("receiverAccountNumber"));

        conductTransactionDto.setAmountOfMoney(commandDescriptor.getParameters().get("amountOfMoney"));

        this.conductTransactionDto = conductTransactionDto;
        return this;
    }
}
