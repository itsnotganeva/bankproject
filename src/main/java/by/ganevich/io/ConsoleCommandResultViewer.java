package by.ganevich.io;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsoleCommandResultViewer {

    public void showResult(CommandResult commandResult) {
        System.out.println(commandResult.getResult().toString());
    }
}
