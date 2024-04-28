package com.neodem.logo.processors;

import java.io.PrintStream;

public interface ConsoleProcessor {
    void process(String arg, PrintStream consoleOut);
}
