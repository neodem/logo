package com.neodem.logo.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MathsTest {
    @Test
    public void allIntegersShouldWorkOnNumbers() {
        assertThat(Maths.allIntegers(new String[]{"5", "3"})).isTrue();
        assertThat(Maths.allIntegers(new String[]{"5.5", "3"})).isFalse();
    }

    @Test
    public void allIntegersShouldSkipOperators() {
        assertThat(Maths.allIntegers(new String[]{"5", "+", "3"})).isTrue();
        assertThat(Maths.allIntegers(new String[]{"5.5", "*", "3"})).isFalse();
    }

    @Test
    public void allIntegersShouldSkipSpaces() {
        assertThat(Maths.allIntegers(new String[]{"5", "+", " ", "3"})).isTrue();
        assertThat(Maths.allIntegers(new String[]{"5.5", "*", " ", "3"})).isFalse();
    }
}
