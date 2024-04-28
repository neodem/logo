package com.neodem.logo.commands;

import com.neodem.logo.args.Arg;
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
    public void handle(List<Arg> args, PrintStream consoleOut) {
        if (args.size() != 2) {
            consoleOut.println("Syntax Error: MAKE expects two arguments");
        } else {
            if (Util.isMakeVariable(args.getFirst().getArgValue())) {
                Object actualValue = Util.convertValue(args.get(1).getArgValue());
                memory.setVariable(Util.stripMakeVariable(args.getFirst().getArgValue()), actualValue);
            } else {
                // evaluate the first variable TODO
            }
        }
    }
}