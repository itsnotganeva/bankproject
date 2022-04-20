package by.ganevich.io;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CommandResult<T> {
    private T result;
}
