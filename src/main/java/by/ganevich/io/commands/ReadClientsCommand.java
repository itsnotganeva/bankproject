package by.ganevich.io.commands;

import by.ganevich.entity.Client;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Getter
@Slf4j
public class ReadClientsCommand implements ICommand {

    private final String commandName = "readClients";

    private final ClientService clientService;

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        return null;
    }

    @Override
    public CommandResult execute(CommandDescriptor commandDescriptor) {

        log.info("Read clients command is called");

        List<Client> clients = clientService.readAll();

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(clients);

        log.info("Read clients command is complete");
        return commandResult;
    }
}
