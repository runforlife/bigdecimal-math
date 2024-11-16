package org.oyushko.big.math;

import org.oyushko.big.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

public class BigDecimalLog10Test extends AbstractBigDecimalTest {
    @Test
    public void testLog10_when1_shouldTrailZeroes() {
        BigDecimal actual = BigDecimalMath.log10(BD("1"), MC);
        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    public void testLog10WithPositivePowersOfTen() {
        MathContext mathContext = new MathContext(50);
        BigDecimal x = BD("1");
        BigDecimal expectedLog10 = BD(0);
        for (int i = 0; i < 20; i++) {
            BigDecimal actualLog10 = BigDecimalMath.log10(x, mathContext);
            assertEquals(true, expectedLog10.compareTo(actualLog10) == 0);

            x = x.multiply(BigDecimal.TEN, mathContext);
            expectedLog10 = expectedLog10.add(BigDecimal.ONE, mathContext);
        }
    }

    @Test
    public void testLog10WithNegativePowersOfTen() {
        MathContext mathContext = new MathContext(50);
        BigDecimal x = BD("1");
        BigDecimal expectedLog10 = BD(0);
        for (int i = 0; i < 20; i++) {
            BigDecimal actualLog10 = BigDecimalMath.log10(x, mathContext);
            assertEquals(true, expectedLog10.compareTo(actualLog10) == 0);

            x = x.divide(BigDecimal.TEN, mathContext);
            expectedLog10 = expectedLog10.subtract(BigDecimal.ONE, mathContext);
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLog10UnlimitedFail() {
        BigDecimalMath.log10(BigDecimal.valueOf(2), MathContext.UNLIMITED);
    }

    @Test
    public void testLog10StripTrailingZeros() {
        MathContext mathContext = new MathContext(50);
        BigDecimal actualLog10 = BigDecimalMath.log10(BD(1), mathContext);
        assertEquals("0", actualLog10.toString());
    }
}
