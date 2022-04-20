package by.ganevich.io.commands;

import by.ganevich.dto.ClientDto;
import by.ganevich.entity.BankAccount;
import by.ganevich.entity.Client;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.mapper.interfaces.ClientMapper;
import by.ganevich.service.BankAccountService;
import by.ganevich.service.ClientService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class ReadBankAccountsCommand extends BaseCommand {

    private final String commandName = "readBankAccounts";

    private final BankAccountService bankAccountService;
    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Valid
    private ClientDto clientDto;

    @Override
    public String getDescriptionValue() {
        String description = "readBankAccounts clientName=?";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Read bank accounts command is called");

        CommandResult commandResult = new CommandResult();
        Client client = clientMapper.toEntity(clientDto);

        if (client == null) {
            commandResult.setResult("The entered client name does not exist!");
            return commandResult;
        } else {
            Set<BankAccount> bankAccounts = bankAccountService.getAllAccountsOfClient(client);
            log.info("Read bank accounts command is complete");
            commandResult.setResult(bankAccounts);
            return commandResult;
        }
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        ClientDto clientDto = clientMapper
                .toDto(clientService.findClientByName(commandDescriptor.getParameters().get("clientName")));
        this.clientDto = clientDto;
        return this;
    }
}
