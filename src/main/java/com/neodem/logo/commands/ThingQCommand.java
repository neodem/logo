package com.neodem.logo.commands;

import com.neodem.logo.args.Arg;
import com.neodem.logo.memory.Memory;
import com.neodem.logo.util.Util;

import java.io.PrintStream;
import java.util.List;

/**
 * outputs TRUE if its input has a value associated to it.
 */
public class ThingQCommand extends BaseCommand implements Command {

    public ThingQCommand(Memory memory) {
        super(memory);
    }

    /**
     * @param args
     * @param consoleOut
     */
    @Override
    public void handle(List<Arg> args, PrintStream consoleOut) {
        if (args.size() == 1) {
            if (memory.getVariable(Util.stripMakeVariable(args.getFirst().getArgValue())) == null) {
                consoleOut.println("TRUE");
            } else {
                consoleOut.println("FALSE");
            }
        } else {
            consoleOut.println("Syntax Error: THING? expects one argument");
        }

    }

    /**
     * @return
     */
    @Override
    public String getCommandKey() {
        return "THING?";
    }
}
