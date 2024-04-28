package com.neodem.logo.commands;

import com.neodem.logo.args.Arg;
import com.neodem.logo.memory.Memory;
import com.neodem.logo.util.Maths;
import com.neodem.logo.util.Util;

import java.io.PrintStream;
import java.util.List;

/**
 * PRINT by default has only one arg. If there are multiple args here we check to see if they are an arithmatic expression
 * else we output a Syntax Error
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
                String arg = args.getFirst().getArgValue();
                if (Util.isGetVariable(arg)) {
                    Object variable = memory.getVariable(Util.stripGetVariable(arg));
                    if (variable == null) {
                        consoleOut.println("Memory Error: Could not locate variable '" + arg + "'");
                    } else {
                        consoleOut.println(variable);
                    }
                } else {
                    consoleOut.println(arg);
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
