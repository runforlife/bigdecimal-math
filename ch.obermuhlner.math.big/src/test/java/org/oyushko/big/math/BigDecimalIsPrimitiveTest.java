package org.oyushko.big.math;

import org.oyushko.big.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BigDecimalIsPrimitiveTest extends AbstractBigDecimalTest {
    @Test
    public void testIsIntValue() {
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(Integer.MIN_VALUE)));
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(Integer.MAX_VALUE)));
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(0)));
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(-55)));
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(33)));
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(-55.0)));
        assertTrue(BigDecimalMath.isIntValue(BigDecimal.valueOf(33.0)));

        assertFalse(BigDecimalMath.isIntValue(BigDecimal.valueOf(Integer.MIN_VALUE).subtract(BigDecimal.ONE)));
        assertFalse(BigDecimalMath.isIntValue(BigDecimal.valueOf(Integer.MAX_VALUE ).add(BigDecimal.ONE)));

        assertFalse(BigDecimalMath.isIntValue(BigDecimal.valueOf(3.333)));
        assertFalse(BigDecimalMath.isIntValue(BigDecimal.valueOf(-5.555)));
    }

    @Test
    public void testIsLongValue() {
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(Long.MIN_VALUE)));
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(Long.MAX_VALUE)));
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(0)));
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(-55)));
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(33)));
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(-55.0)));
        assertTrue(BigDecimalMath.isLongValue(BigDecimal.valueOf(33.0)));

        assertFalse(BigDecimalMath.isLongValue(BigDecimal.valueOf(Long.MIN_VALUE).subtract(BigDecimal.ONE)));
        assertFalse(BigDecimalMath.isLongValue(BigDecimal.valueOf(Long.MAX_VALUE ).add(BigDecimal.ONE)));

        assertFalse(BigDecimalMath.isLongValue(BigDecimal.valueOf(3.333)));
        assertFalse(BigDecimalMath.isLongValue(BigDecimal.valueOf(-5.555)));
    }

    @Test
    public void testIsDoubleValue() {
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(Double.MIN_VALUE)));
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(Double.MAX_VALUE)));
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(-Double.MAX_VALUE)));
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(-Double.MIN_VALUE)));
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(0)));
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(-55)));
        assertTrue(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(33)));
        assertTrue(BigDecimalMath.isDoubleValue(BD("1E-325")));
        assertTrue(BigDecimalMath.isDoubleValue(BD("-1E-325")));
        assertTrue(BigDecimalMath.isDoubleValue(BD("1E-325")));
        assertTrue(BigDecimalMath.isDoubleValue(BD("-1E-325")));

        assertFalse(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(Double.MAX_VALUE).add(BigDecimal.valueOf(1))));
        assertFalse(BigDecimalMath.isDoubleValue(BigDecimal.valueOf(-Double.MAX_VALUE).subtract(BigDecimal.valueOf(1))));
        assertFalse(BigDecimalMath.isDoubleValue(BD("1E309")));
        assertFalse(BigDecimalMath.isDoubleValue(BD("-1E309")));
    }
}
