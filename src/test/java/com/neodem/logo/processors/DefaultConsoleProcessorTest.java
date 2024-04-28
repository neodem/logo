package com.neodem.logo.processors;


import com.neodem.logo.args.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void parseArgsShouldHandleCommand() throws SyntaxError {
        List<Arg> args = processor.parseArgs("print");
        assertThat(args).isNotNull().hasSize(1);
        assertThat(args.get(0)).isInstanceOf(CommandArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("print");
    }

    @Test
    public void parseArgsShouldHandleCommandWithOneArg() throws SyntaxError {
        List<Arg> args = processor.parseArgs("print argument");
        assertThat(args).isNotNull().hasSize(2);
        assertThat(args.get(0)).isInstanceOf(CommandArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("print");
        assertThat(args.get(1)).isInstanceOf(StringArg.class);
        assertThat(((ArgBase)args.get(1)).getArgValue()).isEqualTo("argument");
    }

    @Test
    public void parseArgsShouldHandleCommandWithMathArgNoSpaces() throws SyntaxError {
        String[] tokens = new String[] {"print 3+4"};
        List<Arg> args = processor.parseArgs("print 3+4");
        assertThat(args).isNotNull().hasSize(2);
        assertThat(args.get(0)).isInstanceOf(CommandArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("print");
        assertThat(args.get(1)).isInstanceOf(ArithmaticArg.class);
        assertThat(((ArgBase)args.get(1)).getArgValue()).isEqualTo("3+4");
    }

    @Test
    public void parseArgsShouldHandleCommandWithMathArgWithASpace() throws SyntaxError {
        List<Arg> args = processor.parseArgs("print 3     + 3");
        assertThat(args).isNotNull().hasSize(2);
        assertThat(args.get(0)).isInstanceOf(CommandArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("print");
        assertThat(args.get(1)).isInstanceOf(ArithmaticArg.class);
        assertThat(((ArgBase)args.get(1)).getArgValue()).isEqualTo("3+3");
    }

    @Test
    public void parseArgsShouldHandleMathNoSpaces() throws SyntaxError {
        List<Arg> args = processor.parseArgs("3+4*7");
        assertThat(args).isNotNull().hasSize(1);
        assertThat(args.get(0)).isInstanceOf(ArithmaticArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("3+4*7");
    }

    @Test
    public void parseArgsShouldHandleMathNoSpacesButParens() throws SyntaxError {
        List<Arg> args = processor.parseArgs("3+(4*7)");
        assertThat(args).isNotNull().hasSize(1);
        assertThat(args.get(0)).isInstanceOf(ArithmaticArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("3+(4*7)");
    }

    @Test
    public void parseArgsShouldHandleSimpleExpression() throws SyntaxError {
        List<Arg> args = processor.parseArgs("(something)");
        assertThat(args).isNotNull().hasSize(1);
        assertThat(args.get(0)).isInstanceOf(ExpressionArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("something");
    }
    @Test
    public void parseArgsShouldHandleCommandExpression() throws SyntaxError {
        List<Arg> args = processor.parseArgs("print (something)");
        assertThat(args).isNotNull().hasSize(2);
        assertThat(args.get(0)).isInstanceOf(CommandArg.class);
        assertThat(((ArgBase)args.get(0)).getArgValue()).isEqualTo("print");
        assertThat(args.get(1)).isInstanceOf(ExpressionArg.class);
        assertThat(((ArgBase)args.get(1)).getArgValue()).isEqualTo("something");
    }


}
