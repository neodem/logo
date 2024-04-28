package com.neodem.logo.processors;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DefaultConsoleProcessorTest {
    private DefaultConsoleProcessor processor;

    @BeforeEach
    public void setup() {
        processor = new DefaultConsoleProcessor(null);
    }

    @AfterEach
    public void cleanup() {
        processor = null;
    }

//    @Test
//    public void eval() {
//        String result = processor.evaluateExpression("5+3");
//        assertThat(result).isEqualTo("8");
//    }


}
