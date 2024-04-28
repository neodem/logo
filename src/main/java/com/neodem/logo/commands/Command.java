package com.neodem.logo.commands;

import java.io.PrintStream;
import java.util.List;

public interface Command {
    void handle(List<String> args, PrintStream consoleOut);

    String getCommandKey();
}
