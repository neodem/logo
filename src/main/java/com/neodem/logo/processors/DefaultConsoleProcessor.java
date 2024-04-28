package com.neodem.logo.processors;

import com.neodem.logo.args.*;
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
        List<Arg> args;
        try {
             args = parseArgs(inputString);
        } catch (SyntaxError e) {
            consoleOut.println("Syntax Error: " + e.getMessage() + " in '" + inputString + "'");
            return;
        }

        Arg first = args.getFirst();
        if(first instanceof CommandArg) {
            String commandString = ((CommandArg) first).getArgValue();
            Command command = commandMap.get(commandString.toUpperCase());
            if (command == null) {
                consoleOut.println("Syntax Error: Do not recognize command: '" + commandString + "'");
                return;
            }
            args.removeFirst();
            command.handle(args, consoleOut);
        } else if(first instanceof ArithmaticArg || first instanceof ExpressionArg) {
            String result = first.compute();
            consoleOut.println("RESULT: " + result);
        } else {
            consoleOut.println("Syntax Error: Could not understand: '" + first.getArgValue() + "'");
        }
    }

    protected List<Arg> parseArgs(String inputString) throws SyntaxError {
        List<Arg> args = new ArrayList<>();

        char[] chars = inputString.toCharArray();
        int len = chars.length;
        int i = 0;
        boolean first= true;

        while(i<len) {
            if (Character.isDigit(chars[i])) {
                // read until a non digit or operator
                StringBuilder b = new StringBuilder();
                while (i < len && (isMathy(chars[i]) || chars[i] == ' ')) {
                    b.append(chars[i]);
                    i++;
                }

                String raw = b.toString();
                raw = raw.replaceAll("\\s", "");

                // save as an arithmaticArg
                args.add(new ArithmaticArg(raw));
            } else if (Character.isLetter(chars[i])) {
                StringBuilder b = new StringBuilder();
                while (i < len && chars[i] != ' ') {
                    b.append(chars[i]);
                    i++;
                }

                // save
                if (first) {
                    args.add(new CommandArg(b.toString()));
                    first = false;
                } else {
                    args.add(new StringArg(b.toString()));
                }
            } else if (chars[i] == '(') {
                // read until a )
                StringBuilder b = new StringBuilder();
                i++;
                while (i < len && chars[i] != ')') {
                    b.append(chars[i]);
                    i++;
                }
                // save as an expression
                args.add(new ExpressionArg(b.toString()));
            }
            i++;
        }

        return args;
    }


    private boolean isMathy(char c) {
        return Character.isDigit(c) || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

//
//    private boolean startsWithLetter(String token) {
//        char firstChar = token.charAt(0); // Get the first character of the string
//        return Character.isLetter(firstChar);
//    }
//
//    private List<String> processArgs(String[] tokens) {
//        // strip off the command token
//        List<String> argumentTokens = new ArrayList<>(Arrays.asList(tokens).subList(1, tokens.length));
//
//        // clear out duplicate spaces (if any) in args
//        argumentTokens = Util.removeExtraSpaces(argumentTokens);
//
//        // handle when we have ( or ) or +, etc. touching a number
//        argumentTokens = Util.splitTokens(argumentTokens);
//
//        // add spaces between all tokens (if needed)
//        argumentTokens = Util.addSpaces(argumentTokens);
//
//        // if we have expressions, evaluate them down
//        argumentTokens = evaluateExpressions(argumentTokens);
//
//        return argumentTokens;
//    }
//
//    private List<String> evaluateExpressions(List<String> args) {
//        List<String> modifiedArgs = new ArrayList<>();
//
//        for (int i = 0; i < args.size(); i++) {
//            if (args.getFirst().equals("(")) {
//                // Found the start of an expression
//                StringBuilder expression = new StringBuilder();
//                int depth = 1;
//                i++; // Move to the next element after "("
//
//                // Extract the expression within parentheses
//                while (i < args.size() && depth > 0) {
//                    if (args.get(i).equals("(")) {
//                        depth++;
//                    } else if (args.get(i).equals(")")) {
//                        depth--;
//                        // Break if we have closed the expression
//                        if (depth == 0) {
//                            break;
//                        }
//                    }
//                    expression.append(args.get(i));
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
//                modifiedArgs.add(args.get(i));
//            }
//        }
//
//        return modifiedArgs;
//    }
//
//    protected String evaluateExpression(String expression) {
//        if (Util.isArithmeticExpression(expression)) {
//            Number result = Maths.computeExpression(expression);
//            return result.toString();
//        }
//        return "EXPRESSION";
//    }
}
