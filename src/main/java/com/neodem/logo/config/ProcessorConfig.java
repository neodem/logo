package com.neodem.logo.config;

import com.neodem.logo.commands.*;
import com.neodem.logo.memory.Memory;
import com.neodem.logo.processors.ConsoleProcessor;
import com.neodem.logo.processors.DefaultConsoleProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProcessorConfig {

    @Bean
    public ConsoleProcessor consoleProcessor(Memory memory) {
        Map<String, Command> commandMap = new HashMap<>();
        add(commandMap, new PrintCommand(memory));
        add(commandMap, new MakeCommand(memory));
        add(commandMap, new ThingCommand(memory));
        add(commandMap, new ThingQCommand(memory));


        return new DefaultConsoleProcessor(commandMap);
    }

    private void add(Map<String, Command> commandMap, Command command) {
        commandMap.put(command.getCommandKey(), command);
    }
}
