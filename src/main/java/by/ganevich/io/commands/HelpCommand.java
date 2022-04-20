package by.ganevich.io.commands;

import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class HelpCommand implements ICommand {

    private final String commandName = "help";

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        return null;
    }

    @Override
    public CommandResult execute(CommandDescriptor commandDescriptor) {

        String helpCommand = "createBank: command to create new bank \n"
        + "readBanks: command to read all banks \n"
        + "updateBank: command to update bank \n"
        + "deleteBank: command to delete bank \n\n"
        + "createClient: command to create new client \n"
        + "readClients: command to read all clients \n"
        + "updateClient: command to update client \n"
        + "deleteClient: command to delete client \n"
        + "addClientToBank: command to add client to bank and create a new bank account \n\n"
        + "makeTransaction: command to make transaction \n"
        + "readTransactions: command to read transactions of client \n\n"
        + "readBankAccounts: command to read all bank accounts of client \n\n"
        + "exportCsv: export data from db to csv files \n"
        + "importCsv: import data from csv files to db \n\n"
        + "exit: command to exit from application";

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(helpCommand);
        return commandResult;
    }

}
