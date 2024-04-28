package com.neodem.logo.args;

import com.neodem.logo.util.Maths;

public class ArithmaticArg extends ArgBase implements Arg {
    public ArithmaticArg(String expression) {
        super(expression);
    }

    /**
     * @return
     */
    @Override
    public String compute() {
        return Maths.computeExpression(argValue).toString();
    }
}