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
public class UpdateClientCommand extends BaseCommand {

    private final String commandName = "updateClient";
    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @Valid
    private ClientDto clientDto;

    @Override
    public String getDescriptionValue() {
        String description = "updateClient clientName=? newClientName=? newType=INDIVIDUAL/INDUSTRIAL";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Update client command is called");
        Client client = clientMapper.toEntity(clientDto);
        clientService.save(client);

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(client);

        log.info("Update client command is complete");
        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        ClientDto clientDto = clientMapper
                .toDto(clientService.findClientByName(commandDescriptor.getParameters().get("clientName")));

        clientDto.setName(commandDescriptor.getParameters().get("newClientName"));
        clientDto.setType(commandDescriptor.getParameters().get("newType"));

        this.clientDto = clientDto;
        return this;
    }
}
