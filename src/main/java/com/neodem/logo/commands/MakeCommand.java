package com.neodem.logo.commands;

import com.neodem.logo.args.Arg;
import com.neodem.logo.args.MemoryArg;
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
            Arg key = args.get(0);

            if(key instanceof MemoryArg memoryArg) {
                String keyValue = key.getArgValue();
                // todo validate key value

                if(memoryArg.isSave()) {
                    Arg value = args.get(1);

                    // todo is value an expression?

                    Object actualValue = Util.convertValue(value.getArgValue());
                    memory.setVariable(keyValue, actualValue);
                } else {
                    consoleOut.println("Syntax Error: In a MAKE we only can save variables, not get");
                }
            } else {
                consoleOut.println("Syntax Error: MAKE command expects a key name, not '" + key.getArgValue() + "'");
            }
        }
    }
}