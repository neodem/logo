package com.neodem.logo.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Util {

    public static final String SPECIAL_CHARS = "()+-*/";

    public static boolean isArithmeticExpression(String expression) {
        // Remove whitespace (if any) from the expression string
        expression = expression.replaceAll("\\s", "");

        Stack<Character> stack = new Stack<>();
        boolean expectingOperand = true; // Flag to track whether the next token should be an operand

        for (char c : expression.toCharArray()) {
            if (isOperator(c)) {
                // Check if an operator is followed by an operand
                if (expectingOperand) {
                    return false;
                }
                expectingOperand = true;
            } else if (!isDigit(c)) {
                // If the character is neither an operator nor a digit, it's invalid
                return false;
            } else {
                expectingOperand = false;
            }
            stack.push(c);
        }

        // Check if the last character is a digit (to ensure the expression ends correctly)
        return !stack.isEmpty() && isDigit(stack.peek());
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || c == '(';
    }

    private static boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    public static boolean isGetVariable(String arg) {
        return arg != null && arg.startsWith(":");
    }

    public static boolean isMakeVariable(String arg) {
        return arg != null && arg.startsWith("\"");
    }

    public static String stripMakeVariable(String arg) {
        if (isMakeVariable(arg)) return arg.substring(1);
        return arg;
    }

    public static String stripGetVariable(String arg) {
        if (isGetVariable(arg)) return arg.substring(1);
        return arg;
    }

    /**
     * If numeric, convert to proper number type, else return the input string
     *
     * @param arg
     * @return numeric value if arg is numeric, else returns the input string
     */
    public static Object convertValue(String arg) {
        if (arg != null && !arg.isEmpty()) {
            try {
                // Try parsing as Integer
                return Integer.parseInt(arg);
            } catch (NumberFormatException e1) {
                try {
                    // Try parsing as Double
                    return Double.parseDouble(arg);
                } catch (NumberFormatException e2) {
                    try {
                        // Try parsing as Long
                        return Long.parseLong(arg);
                    } catch (NumberFormatException e3) {
                        // Not a numeric value, return the input string
                        return arg;
                    }
                }
            }
        }
        return null;
    }

    /**
     * in a given list, remove duplicate tokens that have spaces.. allow single instances of the space token
     *
     * @param args
     * @return
     */
    public static List<String> removeExtraSpaces(@NotNull List<String> args) {
        return removeExtras(args, " ");
    }

    public static List<String> removeExtras(@NotNull List<String> args, String toRemove) {
        List<String> result = new ArrayList<>();
        boolean firstInstance = false;
        for (String arg : args) {
            if (toRemove.equals(arg)) {
                if (!firstInstance) {
                    firstInstance = true;
                } else {
                    firstInstance = false;
                    continue;
                }
            } else {
                firstInstance = false;
            }
            result.add(arg);
        }

        return result;
    }

    /**
     * if any token has a paren or an operator in it and it's not alone, split it up.
     * <p>
     * ex: "(3" becomes "(", " ", "3"
     *
     * @param tokens
     * @return
     */
    public static List<String> splitTokens(@NotNull List<String> tokens) {
        List<String> spacedTokens = new ArrayList<>();

        for (String token : tokens) {
            if (token.length() > 1) {
                if (containsParamOrOperator(token)) {
                    // breakup and add
                    List<String> brokenTokens = breakUpToken(token);
                    spacedTokens.addAll(brokenTokens);
                }
            } else {
                spacedTokens.add(token);
            }
        }

        return spacedTokens;
    }

    public static List<String> breakUpToken(String token) {
        List<String> result = new ArrayList<>();

        StringBuilder b = new StringBuilder();
        for (char c : token.toCharArray()) {
            if (SPECIAL_CHARS.indexOf(c) == -1) {
                // not a special character.. append to string (if not a space)
                if (c == 32) continue;
                b.append(c);
            } else {
                if (!b.isEmpty()) {
                    // was the buffer filled with something? add it
                    result.add(b.toString());
                    b = new StringBuilder();
                }
                result.add("" + c);
            }
        }
        if (!b.isEmpty()) {
            result.add(b.toString());
        }
        return result;
    }

    public static boolean containsParamOrOperator(String token) {

        // Iterate through each character in the string
        for (char c : token.toCharArray()) {
            // Check if the character is one of the special characters
            if (SPECIAL_CHARS.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    public static String addSpaces(String expression) {
        if(expression == null) return null;
        StringBuilder b = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if(c != 32) {
                if (isOperator(c)) {
                    b.append(" ");
                    b.append(c);
                    b.append(" ");
                } else {
                    b.append(c);
                }
            }
        }

        return b.toString();
    }

    /**
     * every token should be seperated by spaces
     * @param tokens
     * @return
     */
    public static List<String> addSpaces(@NotNull List<String> tokens) {
        List<String> result = new ArrayList<>();

        boolean alt = true;
        for(String token : tokens) {
            if(!" ".equals(token)) {
                result.add(token);
                if(alt) {
                    result.add(" ");
                    alt = false;
                }
            }
        }

        return result;
    }

    public static String removeExtraSpaces(String input) {
        // Remove leading spaces
        String trimmed = input.replaceAll("^\\s+", "");

        // Condense multiple spaces into a single space
        String condensed = trimmed.replaceAll("\\s{2,}", " ");

        return condensed;
    }
}
