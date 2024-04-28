package com.neodem.logo.memory;

public interface Memory {
    Object getVariable(String name);

    void setVariable(String name, Object value);
}
