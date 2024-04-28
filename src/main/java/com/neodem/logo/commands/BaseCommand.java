package com.neodem.logo.commands;

import com.neodem.logo.memory.Memory;

public abstract class BaseCommand {
    protected final Memory memory;

    public BaseCommand(Memory memory) {
        this.memory = memory;
    }
}
