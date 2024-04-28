package com.neodem.logo.util;

public class Maths {

    /**
     * requires expression to be well formed (spaces break things up)
     * @param expression
     * @return
     */
    public static Number computeExpression(String expression) {
        // Split the expression into operands and operators
        String[] tokens = expression.split(" ");

        if (allIntegers(tokens)) {
            return integerMaths(tokens);
        }

        return doubleMaths(tokens);
    }

    public static Number doubleMaths(String[] tokens) {
        // Convert the first token to a number to initialize the result
        double result = Double.parseDouble(tokens[0]);

        // Iterate over the remaining tokens in pairs (operator followed by operand)
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            // Perform the arithmetic operation based on the operator
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        throw new IllegalArgumentException("Division by zero is not allowed");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        // Return the result
        return result;
    }

    public static Number integerMaths(String[] tokens) {
        // Convert the first token to a number to initialize the result
        int result = Integer.parseInt(tokens[0]);

        // Iterate over the remaining tokens in pairs (operator followed by operand)
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int operand = Integer.parseInt(tokens[i + 1]);

            // Perform the arithmetic operation based on the operator
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        throw new IllegalArgumentException("Division by zero is not allowed");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        // Return the result
        return result;
    }

    protected static boolean allIntegers(String[] tokens) {
        for (String token : tokens) {
            if (!token.matches("-?\\d+(\\.\\d+)?")) {
                continue;
            }

            if (!token.matches("-?\\d+")) {
                return false;
            }
        }
        return true;
    }
}
