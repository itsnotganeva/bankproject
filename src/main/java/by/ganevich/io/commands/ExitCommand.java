package by.ganevich.io.commands;

import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class ExitCommand implements ICommand {

    private final String commandName = "exit";

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        return null;
    }

    @Override
    public CommandResult execute(CommandDescriptor commandDescriptor) {
        System.exit(0);
        return null;
    }
}
