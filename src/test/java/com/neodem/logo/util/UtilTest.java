package com.neodem.logo.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilTest {

    @Test
    public void containsParamOrOperatorShouldWorkForSingles() {
        assertThat(Util.containsParamOrOperator(")")).isTrue();
        assertThat(Util.containsParamOrOperator("(")).isTrue();
        assertThat(Util.containsParamOrOperator("+")).isTrue();
        assertThat(Util.containsParamOrOperator("-")).isTrue();
        assertThat(Util.containsParamOrOperator("*")).isTrue();
        assertThat(Util.containsParamOrOperator("/")).isTrue();
    }

    @Test
    public void containsParamOrOperatorShouldWorkForStrings() {
        assertThat(Util.containsParamOrOperator("asdad)")).isTrue();
        assertThat(Util.containsParamOrOperator("f(dasd")).isTrue();
        assertThat(Util.containsParamOrOperator("ggg+asd")).isTrue();
        assertThat(Util.containsParamOrOperator("asd-")).isTrue();
        assertThat(Util.containsParamOrOperator("*ddd")).isTrue();
        assertThat(Util.containsParamOrOperator("asddddasd/")).isTrue();
    }

    @Test
    public void containsParamOrOperatorShouldWorkForStringsNotContaining() {
        assertThat(Util.containsParamOrOperator("asdad")).isFalse();
        assertThat(Util.containsParamOrOperator("fdasd")).isFalse();
        assertThat(Util.containsParamOrOperator("gggasd")).isFalse();
        assertThat(Util.containsParamOrOperator("asd")).isFalse();
        assertThat(Util.containsParamOrOperator("ddd")).isFalse();
        assertThat(Util.containsParamOrOperator("asddddasd")).isFalse();
    }

    @Test
    public void breakUpTokenShouldSplitOnSpecials() {
        assertThat(Util.breakUpToken("asdad)")).containsExactly("asdad", ")");
        assertThat(Util.breakUpToken("(23")).containsExactly("(", "23");
        assertThat(Util.breakUpToken("3+")).containsExactly("3", "+");
        assertThat(Util.breakUpToken("3+7")).containsExactly("3", "+", "7");
    }

    @Test
    public void breakUpTokenShouldHandleSpaces() {
        assertThat(Util.breakUpToken("(52 + 23)")).containsExactly("(", "52", "+", "23", ")");
    }

    @Test
    public void removeExtraSpacesShouldPassAlongUntouched() {
        assertThat(Util.removeExtraSpaces(List.of("token"))).containsExactly("token");
        assertThat(Util.removeExtraSpaces(List.of("token", "t2"))).containsExactly("token", "t2");
    }

    @Test
    public void removeExtraSpacesShouldAllowSingleSpaces() {
        assertThat(Util.removeExtraSpaces(List.of("token", " "))).containsExactly("token", " ");
        assertThat(Util.removeExtraSpaces(List.of(" ", "token"))).containsExactly(" ", "token");
        assertThat(Util.removeExtraSpaces(List.of(" ", "token", " "))).containsExactly(" ", "token", " ");
    }

    @Test
    public void removeExtraSpacesShouldStripMultipleSpaces() {
        assertThat(Util.removeExtraSpaces(List.of("token", " ", " "))).containsExactly("token", " ");
        assertThat(Util.removeExtraSpaces(List.of(" ", " ", "token"))).containsExactly(" ", "token");
        assertThat(Util.removeExtraSpaces(List.of(" ", "token", " ", " "))).containsExactly(" ", "token", " ");
    }

    @Test
    public void splitTokensShouldWorkWithNumbers() {
        assertThat(Util.splitTokens(List.of("5+2"))).containsExactly("5", "+", "2");
        assertThat(Util.splitTokens(List.of("52+23"))).containsExactly("52", "+", "23");
    }

    @Test
    public void splitTokensShouldHandleParens() {
        assertThat(Util.splitTokens(List.of("(5+2)"))).containsExactly("(", "5", "+", "2", ")");
        assertThat(Util.splitTokens(List.of("(52+22)"))).containsExactly("(", "52", "+", "22", ")");
    }

    @Test
    public void splitTokensShouldHandleParensAndSpaces() {
        assertThat(Util.splitTokens(List.of("(52 + 22)"))).containsExactly("(", "52", "+", "22", ")");
    }

    @Test
    public void splitTokensShouldHandleSpaces() {
        assertThat(Util.splitTokens(List.of("5+    2"))).containsExactly("5", "+", "2");
    }
//
//    @Test
//    public void isArithmeticExpressionShouldFindNormalCases() {
//        assertThat(Util.isArithmeticExpression("5+3")).isTrue();
//        assertThat(Util.isArithmeticExpression("5 + 3")).isTrue();
//        assertThat(Util.isArithmeticExpression("5 * 3")).isTrue();
//        assertThat(Util.isArithmeticExpression("12 - 23")).isTrue();
//
//
//        assertThat(Util.isArithmeticExpression("12 a 23")).isFalse();
//    }
}
