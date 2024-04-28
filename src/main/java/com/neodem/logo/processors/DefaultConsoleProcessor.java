package com.neodem.logo.processors;

import com.neodem.logo.args.Arg;
import com.neodem.logo.commands.Command;
import com.neodem.logo.util.Maths;
import com.neodem.logo.util.Util;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultConsoleProcessor implements ConsoleProcessor {

    private final Map<String, Command> commandMap;

    public DefaultConsoleProcessor(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * @param inputString
     * @param consoleOut
     * @return
     */
    @Override
    public void process(String inputString, PrintStream consoleOut) {
        if (inputString == null || inputString.isEmpty()) {
            return;
        }

        String[] tokens = inputString.split("\\s+");
        String commandString = tokens[0];
        List<String> args = processArgs(tokens);

        if (commandString == null || commandString.isEmpty()) {
            consoleOut.println("Syntax Error: Could not recognize command in '" + inputString + "'");
            return;
        }
        Command command = commandMap.get(commandString.toUpperCase());
        if (command == null) {
            consoleOut.println("Syntax Error: Do not recognize command: '" + commandString + "'");
            return;
        }

        command.handle(args, consoleOut);
    }

    private List<String> processArgs(String[] tokens) {
        List<String> argumentTokens = new ArrayList<>(Arrays.asList(tokens).subList(1, tokens.length));
        argumentTokens = Util.removeExtraSpaces(argumentTokens);
        argumentTokens = Util.splitTokens(argumentTokens);
        argumentTokens = evaluateExpressions(argumentTokens);
        return argumentTokens;
    }

    private List<String> evaluateExpressions(List<String> args) {
        List<String> modifiedArgs = new ArrayList<>();

        for (int i = 0; i < args.size(); i++) {
            if (args.getFirst().equals("(")) {
                // Found the start of an expression
                StringBuilder expression = new StringBuilder();
                int depth = 1;
                i++; // Move to the next element after "("

                // Extract the expression within parentheses
                while (i < args.size() && depth > 0) {
                    if (args.get(i).equals("(")) {
                        depth++;
                    } else if (args.get(i).equals(")")) {
                        depth--;
                        // Break if we have closed the expression
                        if (depth == 0) {
                            break;
                        }
                    }
                    expression.append(args.get(i));
                    i++;
                }

                // Evaluate the expression and add the result
                String result = evaluateExpression(expression.toString());
                modifiedArgs.add(result);

                // Move i back one step because the loop will increment it again
                i--;
            } else {
                // Not an expression, just add it to the modified args
                modifiedArgs.add(args.get(i));
            }
        }

        return modifiedArgs;
    }

    /**
     * Iterate through the tokens and sort into Arg types
     *
     * @param argumentTokens
     * @return
     */
    private List<Arg> convertToArgs(List<String> argumentTokens) {
        List<Arg> args = new ArrayList<>();
//
//        for (int i = 0; i < argumentTokens.size(); i++) {
//            if (argumentTokens.get(i).startsWith("(")) {
//                // Found the start of an expression
//                StringBuilder expression = new StringBuilder();
//                int depth = 1;
//                i++; // Move to the next element after "("
//
//                // Extract the expression within parentheses
//                while (i < args.length && depth > 0) {
//                    if (args[i].equals("(")) {
//                        depth++;
//                    } else if (args[i].equals(")")) {
//                        depth--;
//                    } else {
//                        expression.append(args[i]);
//                    }
//                    i++;
//                }
//
//                // Evaluate the expression and add the result
//                String result = evaluateExpression(expression.toString());
//                modifiedArgs.add(result);
//
//                // Move i back one step because the loop will increment it again
//                i--;
//            } else {
//                // Not an expression, just add it to the modified args
//                modifiedArgs.add(args[i]);
//            }
//        }
//
        return args;
    }


    protected List<String> handleExpressions(List<String> args) {
        List<String> modifiedArgs = new ArrayList<>();

//        for (int i = 0; i < args.size(); i++) {
//            if (args[i].equals("(")) {
//                // Found the start of an expression
//                StringBuilder expression = new StringBuilder();
//                int depth = 1;
//                i++; // Move to the next element after "("
//
//                // Extract the expression within parentheses
//                while (i < args.length && depth > 0) {
//                    if (args[i].equals("(")) {
//                        depth++;
//                    } else if (args[i].equals(")")) {
//                        depth--;
//                    } else {
//                        expression.append(args[i]);
//                    }
//                    i++;
//                }
//
//                // Evaluate the expression and add the result
//                String result = evaluateExpression(expression.toString());
//                modifiedArgs.add(result);
//
//                // Move i back one step because the loop will increment it again
//                i--;
//            } else {
//                // Not an expression, just add it to the modified args
//                modifiedArgs.add(args[i]);
//            }
//        }

        return modifiedArgs;
    }

    protected String evaluateExpression(String expression) {
        if (Util.isArithmeticExpression(expression)) {
            Number result = Maths.computeExpression(expression);
            return result.toString();
        }
        return "EXPRESSION";
    }
}
