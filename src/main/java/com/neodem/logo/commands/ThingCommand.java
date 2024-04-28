package com.neodem.logo.commands;

import com.neodem.logo.memory.Memory;

import java.io.PrintStream;
import java.util.List;

public class ThingCommand extends BaseCommand implements Command {

    public ThingCommand(Memory memory) {
        super(memory);
    }

    /**
     * @param args
     * @param consoleOut
     */
    @Override
    public void handle(List<String> args, PrintStream consoleOut) {

    }

    /**
     * @return
     */
    @Override
    public String getCommandKey() {
        return "THING";
    }
}
