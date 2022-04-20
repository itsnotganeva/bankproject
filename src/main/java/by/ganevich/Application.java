package by.ganevich;


import by.ganevich.exception.CommandNotFoundException;
import by.ganevich.io.ConsoleInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    private static ConsoleInterpreter interpreter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }
}
