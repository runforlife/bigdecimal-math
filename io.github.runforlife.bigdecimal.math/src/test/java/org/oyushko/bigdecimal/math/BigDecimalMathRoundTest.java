package org.oyushko.bigdecimal.math;

import org.oyushko.bigdecimal.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BigDecimalMathRoundTest extends AbstractBigDecimalTest {
    @Test
    public void testRound() {
        MathContext mc = new MathContext(5);

        for (String s : Arrays.asList("1.234567", "1.23", "12.34567", "0.001234567")) {
            BigDecimal value = BD(s);
            assertEquals(value.round(mc), BigDecimalMath.round(value, mc));
        }
    }

    @Test
    public void testRoundWithTrailingZeroes() {
        MathContext mc = new MathContext(5);

        assertEquals("0.0000", BigDecimalMath.roundWithTrailingZeroes(BD("0"), mc).toString());
        assertEquals("0.0000", BigDecimalMath.roundWithTrailingZeroes(BD("0.00000000000"), mc).toString());

        assertEquals("1.2345", BigDecimalMath.roundWithTrailingZeroes(BD("1.2345"), mc).toString());
        assertEquals("123.45", BigDecimalMath.roundWithTrailingZeroes(BD("123.45"), mc).toString());
        assertEquals("0.0012345", BigDecimalMath.roundWithTrailingZeroes(BD("0.0012345"), mc).toString());

        assertEquals("1.2346", BigDecimalMath.roundWithTrailingZeroes(BD("1.234567"), mc).toString());
        assertEquals("1.2300", BigDecimalMath.roundWithTrailingZeroes(BD("1.23"), mc).toString());
        assertEquals("1.2300", BigDecimalMath.roundWithTrailingZeroes(BD("1.230000"), mc).toString());
        assertEquals("123.46", BigDecimalMath.roundWithTrailingZeroes(BD("123.4567"), mc).toString());
        assertEquals("0.0012346", BigDecimalMath.roundWithTrailingZeroes(BD("0.001234567"), mc).toString());
        assertEquals("0.0012300", BigDecimalMath.roundWithTrailingZeroes(BD("0.00123"), mc).toString());

        assertEquals("1.2346E+100", BigDecimalMath.roundWithTrailingZeroes(BD("1.234567E+100"), mc).toString());
        assertEquals("1.2346E-100", BigDecimalMath.roundWithTrailingZeroes(BD("1.234567E-100"), mc).toString());
        assertEquals("1.2300E+100", BigDecimalMath.roundWithTrailingZeroes(BD("1.23E+100"), mc).toString());
        assertEquals("1.2300E-100", BigDecimalMath.roundWithTrailingZeroes(BD("1.23E-100"), mc).toString());
        assertEquals("1.2346E+999999999", BigDecimalMath.roundWithTrailingZeroes(BD("1.234567E+999999999"), mc).toString());
        assertEquals("1.2346E-999999999", BigDecimalMath.roundWithTrailingZeroes(BD("1.234567E-999999999"), mc).toString());
        assertEquals("1.2300E+999999999", BigDecimalMath.roundWithTrailingZeroes(BD("1.23E+999999999"), mc).toString());
        assertEquals("1.2300E-999999999", BigDecimalMath.roundWithTrailingZeroes(BD("1.23E-999999999"), mc).toString());
    }
}
