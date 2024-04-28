package com.neodem.logo.config;

import com.neodem.logo.memory.Memory;
import com.neodem.logo.memory.TestMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    @Bean
    public Memory memory() {
        return new TestMemory();
    }
}
