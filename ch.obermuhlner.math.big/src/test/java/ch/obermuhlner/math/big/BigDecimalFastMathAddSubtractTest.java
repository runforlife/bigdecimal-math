package ch.obermuhlner.math.big;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

public class BigDecimalFastMathAddSubtractTest {
    private static final MathContext DECIMAL_7_DIGITS = MathContext.DECIMAL32;
    
    @Test
    public void add() {
        assertEquals(BD("0"), BigDecimalMath.add(BD("0"), BD("0"), DECIMAL_7_DIGITS));
        assertEquals(BD("1"), BigDecimalMath.add(BD("1"), BD("0"), DECIMAL_7_DIGITS));
        assertEquals(BD("1"), BigDecimalMath.add(BD("0"), BD("1"), DECIMAL_7_DIGITS));
        assertEquals(BD("1234568"), BigDecimalMath.add(BD("0.9999999"), BD("1234567"), DECIMAL_7_DIGITS));
        assertEquals(BD("1234566"), BigDecimalMath.add(BD("-0.9999999"), BD("1234567"), DECIMAL_7_DIGITS));
        assertEquals(BD("1234568"), BigDecimalMath.add(BD("0.9999999"), BD("1234567"), DECIMAL_7_DIGITS));
        assertEquals(BD("1234566"), BigDecimalMath.add(BD("-0.9999999"), BD("1234567"), DECIMAL_7_DIGITS));
    }

    private static BigDecimal BD(String value) {
        return new BigDecimal(value);
    }
}