package by.ganevich.io;

import by.ganevich.io.commands.ICommand;
import by.ganevich.io.factory.CommandFactory;
import by.ganevich.validator.CustomValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CommandExecutor {
    private final CommandFactory commandFactory;
    private final CustomValidator<ICommand> customValidator;

    CommandResult executeCommand(CommandDescriptor commandDescriptor) throws IOException {
        ICommand command = commandFactory.getCommand(commandDescriptor);

        if (commandDescriptor.getParameters().containsValue("help")) {
            return command.execute(commandDescriptor);
        } else {
            command.setDto(commandDescriptor);
            if (customValidator.validateCommand(command).isEmpty()) {
                return command.execute(commandDescriptor);
            } else {
                CommandResult commandResult = new CommandResult();
                commandResult.setResult(customValidator.validateCommand(command));
                return commandResult;
            }
        }

    }
}
