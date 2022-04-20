package by.ganevich.io;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CommandDescriptor {

    private String commandName;

    private Map<String, String> parameters;

}
