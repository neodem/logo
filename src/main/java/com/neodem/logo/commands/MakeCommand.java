package com.neodem.logo.commands;

import com.neodem.logo.memory.Memory;
import com.neodem.logo.util.Util;

import java.io.PrintStream;
import java.util.List;

/**
 * Takes 2 inputs, the first of which must be a word. It treats the word as a variable name, and makes the second input be
 * the value (thing) of the variable.
 * <p>
 * Make evaluates its first input (TODO not implemented yet)
 */
public class MakeCommand extends BaseCommand implements Command {

    public MakeCommand(Memory memory) {
        super(memory);
    }

    /**
     * @return
     */
    @Override
    public String getCommandKey() {
        return "MAKE";
    }

    /**
     * @param args
     * @param consoleOut
     */
    @Override
    public void handle(List<String> args, PrintStream consoleOut) {
        if (args.size() != 2) {
            consoleOut.println("Syntax Error: MAKE expects two arguments");
        } else {
            if (Util.isMakeVariable(args.getFirst())) {
                Object actualValue = Util.convertValue(args.get(1));
                memory.setVariable(Util.stripMakeVariable(args.getFirst()), actualValue);
            } else {
                // evaluate the first variable TODO
            }
        }
    }
}