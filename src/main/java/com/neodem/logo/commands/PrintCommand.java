package com.neodem.logo.commands;

import com.neodem.logo.args.Arg;
import com.neodem.logo.args.MemoryArg;
import com.neodem.logo.memory.Memory;
import com.neodem.logo.util.Maths;
import com.neodem.logo.util.Util;

import java.io.PrintStream;
import java.util.List;

/**
 * PRINT by default has only one arg. It can be a `word` a `list` or a `number` which is just printed to the screen.
 * However, PRINT in a paren will combine everything: (PRINT something something)
 */
public class PrintCommand extends BaseCommand implements Command {

    public PrintCommand(Memory memory) {
        super(memory);
    }

    /**
     * @return
     */
    @Override
    public String getCommandKey() {
        return "PRINT";
    }

    /**
     * @param args
     * @param consoleOut
     */
    @Override
    public void handle(List<Arg> args, PrintStream consoleOut) {
        if (args.isEmpty()) {
            consoleOut.println();
        } else {
            if (args.size() == 1) {
                Arg argument = args.getFirst();
                if(argument instanceof MemoryArg memoryArg) {
                    String keyName = memoryArg.getArgValue();
                    if(memoryArg.isSave()) {
                        consoleOut.println("Syntax Error: In a print we only can get variables, not save");
                    } else {
                        Object variable = memory.getVariable(keyName);
                        if (variable == null) {
                            consoleOut.println("Memory Error: Could not locate variable '" + keyName + "'");
                        } else {
                            consoleOut.println(variable);
                        }
                    }
                } else {
                    consoleOut.println(argument.getArgValue());
                }
            } else {
//                String expression = String.join(" ", args);
//                if (!Util.isArithmeticExpression(expression)) {
//                    consoleOut.println("Syntax Error: PRINT expects one argument or a math expression");
//                    return;
//                }
//
//                Number number = Maths.computeExpression(expression);
//                consoleOut.println(number);
            }
        }
    }
}
