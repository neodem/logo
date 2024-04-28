package com.neodem.logo.memory;

import java.util.HashMap;
import java.util.Map;

public class TestMemory implements Memory {

    private final Map<String, Object> memory;

    public TestMemory() {
        this.memory = new HashMap<>();
        this.memory.put("TEST", "testVariable");
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Object getVariable(String name) {
        return memory.get(name);
    }

    /**
     * @param name
     * @param value
     */
    @Override
    public void setVariable(String name, Object value) {
        memory.put(name, value);
    }
}
