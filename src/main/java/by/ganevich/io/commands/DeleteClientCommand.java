package by.ganevich.io.commands;

import by.ganevich.dto.ClientDto;
import by.ganevich.entity.Client;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.mapper.interfaces.ClientMapper;
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
public class DeleteClientCommand extends BaseCommand {

    private final String commandName = "deleteClient";
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Valid
    private ClientDto clientDto;


    @Override
    public String getDescriptionValue() {
        String description = "deleteClient clientName=?";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Delete client command is called");

        String result;

        Client client = clientMapper.toEntity(clientDto);
        if (client == null) {
            result = "Client already removed!";
        } else {
            clientService.removeClient(client);
            result = "Client is successfully removed!";
        }

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(result);

        log.info("Delete client command is complete");
        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        ClientDto clientDto = clientMapper
                .toDto(clientService.findClientByName(commandDescriptor.getParameters().get("clientName")));
        this.clientDto = clientDto;
        return this;
    }
}
