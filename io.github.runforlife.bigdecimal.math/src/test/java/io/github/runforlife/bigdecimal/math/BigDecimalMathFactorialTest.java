package io.github.runforlife.bigdecimal.math;

import io.github.runforlife.bigdecimal.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BigDecimalMathFactorialTest extends AbstractBigDecimalTest {
    @Test
    public void testFactorialInt() {
        assertEquals(BD(1), BigDecimalMath.factorial(0));
        assertEquals(BD(1), BigDecimalMath.factorial(1));
        assertEquals(BD(2), BigDecimalMath.factorial(2));
        assertEquals(BD(6), BigDecimalMath.factorial(3));
        assertEquals(BD(24), BigDecimalMath.factorial(4));
        assertEquals(BD(120), BigDecimalMath.factorial(5));

        assertEquals(
                BigDecimalMath.toBigDecimal("9425947759838359420851623124482936749562312794702543768327889353416977599316221476503087861591808346911623490003549599583369706302603264000000000000000000000000"),
                BigDecimalMath.factorial(101));

        BigDecimal expected = BigDecimal.ONE;
        for (int n = 1; n < 1000; n++) {
            expected = expected.multiply(BigDecimal.valueOf(n));
            assertEquals(expected, BigDecimalMath.factorial(n));
        }
    }

    @Test(expected = ArithmeticException.class)
    public void testFactorialIntNegative() {
        BigDecimalMath.factorial(-1);
    }

    @Test
    public void testFactorial() {
        // Result from wolframalpha.com: 1.5!
        BigDecimal expected = BD("1.3293403881791370204736256125058588870981620920917903461603558423896834634432741360312129925539084990621701");
        assertPrecisionCalculation(
                expected,
                mathContext -> BigDecimalMath.factorial(BD("1.5"), mathContext),
                10);
    }

    @Test
    public void testFactorialNegative() {
        // Result from wolframalpha.com: (-1.5)!
        BigDecimal expected = BD("-3.544907701811032054596334966682290365595098912244774256427615579705822569182064362749901313477089330832453647248");
        assertPrecisionCalculation(
                expected,
                mathContext -> BigDecimalMath.factorial(BD("-1.5"), mathContext),
                10);
    }

    @Test
    public void testFactorialIntegerValues() {
        assertEquals(BigDecimalMath.round(BD(1), MC), BigDecimalMath.factorial(BigDecimal.valueOf(0), MC));
        assertEquals(BigDecimalMath.round(BD(1), MC), BigDecimalMath.factorial(BigDecimal.valueOf(1), MC));
        assertEquals(BigDecimalMath.round(BD(2), MC), BigDecimalMath.factorial(BigDecimal.valueOf(2), MC));
        assertEquals(BigDecimalMath.round(BD(6), MC), BigDecimalMath.factorial(BigDecimal.valueOf(3), MC));
        assertEquals(BigDecimalMath.round(BD(24), MC), BigDecimalMath.factorial(BigDecimal.valueOf(4), MC));
        assertEquals(BigDecimalMath.round(BD(120), MC), BigDecimalMath.factorial(BigDecimal.valueOf(5), MC));
    }

    @Test(expected = ArithmeticException.class)
    public void testFactorialNegative1() {
        BigDecimalMath.factorial(BigDecimal.valueOf(-1), MC);
    }

    @Test(expected = ArithmeticException.class)
    public void testFactorialNegative2() {
        BigDecimalMath.factorial(BigDecimal.valueOf(-2), MC);
    }
}
