package by.ganevich.io;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConsoleLineParser {

    public CommandDescriptor parseInput(String input) {

        CommandDescriptor commandDescriptor = new CommandDescriptor();

        String[] command = input.split(" ");

        String commandName = command[0];

        commandDescriptor.setCommandName(commandName);

        Map<String, String> parsedCommand = new HashMap<>();

        if (command.length > 1 && command[1].equals("help")) {
            parsedCommand = new HashMap<>();
            parsedCommand.put("help", "help");
        } else {
            for (String keyValue : input.substring(input.indexOf(" ")+1).split(" ")) {
                String[] pairs = keyValue.split("=", 2);
                parsedCommand.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
            }
        }

        commandDescriptor.setParameters(parsedCommand);
        return commandDescriptor;
    }
}
