package com.neodem.logo.args;

public abstract class ArgBase {
    protected final String argValue;

    public ArgBase(String argValue) {
        this.argValue = argValue;
    }

    public String getArgValue() {
        return argValue;
    }

    public String compute() {
        return argValue;
    }
}
