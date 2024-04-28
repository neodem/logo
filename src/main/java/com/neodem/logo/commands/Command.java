package com.neodem.logo.commands;

import com.neodem.logo.args.Arg;

import java.io.PrintStream;
import java.util.List;

public interface Command {
    void handle(List<Arg> args, PrintStream consoleOut);

    String getCommandKey();
}
