package by.ganevich.io.commands;

import by.ganevich.dto.BankAccountDto;
import by.ganevich.dto.BankDto;
import by.ganevich.dto.ClientDto;
import by.ganevich.entity.BankAccount;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.mapper.interfaces.BankAccountMapper;
import by.ganevich.mapper.interfaces.BankMapper;
import by.ganevich.mapper.interfaces.ClientMapper;
import by.ganevich.service.BankAccountService;
import by.ganevich.service.BankService;
import by.ganevich.service.ClientService;
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
public class AddClientToBankCommand extends BaseCommand {

    private final String commandName = "addClientToBank";
    private final ClientService clientService;
    private final BankService bankService;
    private final BankAccountService bankAccountService;
    private final BankMapper bankMapper;
    private final ClientMapper clientMapper;
    private final BankAccountMapper bankAccountMapper;

    @Valid
    private BankAccountDto bankAccountDto;

    @Override
    public String getDescriptionValue() {
        String description = "addClientToBank clientName=? bankName=? accountNumber=? currency=? amountOfMoney=?";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Add client to bank command is called");

        BankAccount bankAccount = bankAccountMapper.toEntity(bankAccountDto);

        bankAccountService.save(bankAccount);

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(bankAccount);

        log.info("Add client to bank command is complete");

        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {

        BankAccountDto bankAccountDto = new BankAccountDto();

        BankDto bankDto = bankMapper
                .toDto(bankService.findBankByName(commandDescriptor.getParameters().get("bankName")));
        ClientDto clientDto = clientMapper
                .toDto(clientService.findClientByName(commandDescriptor.getParameters().get("clientName")));

        bankAccountDto.setOwner(clientDto);
        bankAccountDto.setBankProducer(bankDto);
        bankAccountDto.setCurrency(commandDescriptor.getParameters().get("currency"));
        bankAccountDto.setAmountOfMoney(commandDescriptor.getParameters().get("amountOfMoney"));
        bankAccountDto.setNumber(commandDescriptor.getParameters().get("accountNumber"));

        this.bankAccountDto = bankAccountDto;
        return this;
    }
}
