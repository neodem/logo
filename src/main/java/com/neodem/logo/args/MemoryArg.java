package com.neodem.logo.args;

public class MemoryArg extends ArgBase implements Arg {
    private final boolean save;

    public MemoryArg(String expression, boolean save) {
        super(expression);
        this.save = save;
    }

    public boolean isSave() {
        return save;
    }
}
