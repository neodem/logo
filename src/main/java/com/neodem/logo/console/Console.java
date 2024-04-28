package com.neodem.logo.console;

import com.neodem.logo.processors.ConsoleProcessor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {

    private final Scanner scanner;
    private final PrintStream consoleOut;
    private final ConsoleProcessor consoleProcessor;

    public Console(InputStream consoleIn, PrintStream consoleOut, ConsoleProcessor consoleProcessor) {
        this.scanner = new Scanner(consoleIn);
        this.consoleOut = consoleOut;
        this.consoleProcessor = consoleProcessor;
    }

    public void start() {
        consoleOut.println("Logo");
        consoleOut.println("Enter 'exit' to quit the program.");

        while (true) {
            consoleOut.print("? ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            consoleProcessor.process(input, consoleOut);
        }

        consoleOut.println("Exiting. Goodbye!");
        scanner.close();
    }
}
