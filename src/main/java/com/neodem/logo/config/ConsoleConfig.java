package com.neodem.logo.config;

import com.neodem.logo.console.Console;
import com.neodem.logo.processors.ConsoleProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
public class ConsoleConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Console console = (Console) event.getApplicationContext().getBean("console");
        console.start();
        //TODO I'm not a fan of this.. seems to load before the app is fully up and running
    }

    @Bean
    public Console console(ConsoleProcessor consoleProcessor) {
        PrintStream consoleOut = System.out;
        InputStream consoleIn = System.in;
        return new Console(consoleIn, consoleOut, consoleProcessor);
    }
}
