package by.ganevich.io.commands;

import by.ganevich.entity.Bank;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.service.BankService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Getter
@Slf4j
public class ReadBanksCommand implements ICommand {

    private final String commandName = "readBanks";

    private final BankService bankService;

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        return null;
    }

    @Override
    public CommandResult execute(CommandDescriptor commandDescriptor) {

        log.info("Read banks command is called");

        List<Bank> banks = bankService.readAll();

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(banks);

        log.info("Read bank accounts command is complete");
        return commandResult;
    }
}
