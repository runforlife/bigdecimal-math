package org.oyushko.big.math;

import org.oyushko.big.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.oyushko.big.math.BigDecimalMathTest.assertThrows;
import static org.junit.Assert.assertEquals;

public class BigDecimalArithmeticTest extends AbstractBigDecimalTest {
    private static final MathContext D7 = MathContext.DECIMAL32;
    
    @Test
    public void add() {
        assertEquals(BD("0"), BigDecimalMath.add(BD("0"), BD("0"), D7));
        assertEquals(BD("1"), BigDecimalMath.add(BD("1"), BD("0"), D7));
        assertEquals(BD("1"), BigDecimalMath.add(BD("0"), BD("1"), D7));
        assertEquals(BD("2"), BigDecimalMath.add(BD("1"), BD("1"), D7));

        assertEquals(BD("1234568"), BigDecimalMath.add(BD("0.9999999"), BD("1234567"), D7));
        assertEquals(BD("1234566"), BigDecimalMath.add(BD("-0.9999999"), BD("1234567"), D7));
        assertEquals(BD("1234568"), BigDecimalMath.add(BD("0.9999999"), BD("1234567"), D7));
        assertEquals(BD("1234566"), BigDecimalMath.add(BD("-0.9999999"), BD("1234567"), D7));

        assertEquals(BD("2.444445"), BigDecimalMath.add(BD("1.444444499999"), BD("1.00000000001"), D7));
        assertEquals(BD("2.444444"), BigDecimalMath.add(BD("1.444444499999"), BD("1.000000000001"), D7));
    }

    @Test
    public void subtract() {
        assertEquals(BD("0"), BigDecimalMath.subtract(BD("0"), BD("0"), D7));
        assertEquals(BD("1"), BigDecimalMath.subtract(BD("1"), BD("0"), D7));
        assertEquals(BD("-1"), BigDecimalMath.subtract(BD("0"), BD("1"), D7));
        assertEquals(BD("0"), BigDecimalMath.subtract(BD("1"), BD("1"), D7));

        assertEquals(BD("1234566"), BigDecimalMath.subtract(BD("1234567"), BD("0.9999999"), D7));
        assertEquals(BD("1234568"), BigDecimalMath.subtract(BD("1234567"), BD("-0.9999999"), D7));
        assertEquals(BD("1234566"), BigDecimalMath.subtract(BD("1234567"), BD("0.9999999"), D7));
        assertEquals(BD("1234568"), BigDecimalMath.subtract(BD("1234567"), BD("-0.9999999"), D7));

        assertEquals(BD("0.4444444"), BigDecimalMath.subtract(BD("1.44444445001"), BD("1.00000000001"), D7));
        assertEquals(BD("0.4444445"), BigDecimalMath.subtract(BD("1.44444445001"), BD("1.000000000001"), D7));
    }

    @Test
    public void multiply() {
        assertEquals(BD("0"), BigDecimalMath.multiply(BD("0"), BD("0"), D7));
        assertEquals(BD("0"), BigDecimalMath.multiply(BD("1"), BD("0"), D7));
        assertEquals(BD("0"), BigDecimalMath.multiply(BD("0"), BD("1"), D7));
        assertEquals(BD("1"), BigDecimalMath.multiply(BD("1"), BD("1"), D7));

        assertEquals(BD("1.524156E+12"), BigDecimalMath.multiply(BD("1234567"), BD("1234567"), D7));
        assertEquals(BD("1.219326E+23"), BigDecimalMath.multiply(BD("123456789012"), BD("987654321098"), D7));

        assertEquals(BD("1.234568E+10"), BigDecimalMath.multiply(BD("123456789012"), BD("0.1000000000001"), D7));
        assertEquals(BD("1.234568E+10"), BigDecimalMath.multiply(BD("123456789012"), BD("0.10000000000001"), D7));
    }

    @Test
    public void divide() {
        assertEquals(BD("0"), BigDecimalMath.divide(BD("0"), BD("1"), D7));
        assertEquals(BD("1"), BigDecimalMath.divide(BD("1"), BD("1"), D7));

        assertEquals(BD("1"), BigDecimalMath.divide(BD("1234567"), BD("1234567"), D7));
        assertEquals(BD("0.1250000"), BigDecimalMath.divide(BD("123456789012"), BD("987654321098"), D7));

        assertEquals(BD("1.234568E+12"), BigDecimalMath.divide(BD("123456789012"), BD("0.1000000000001"), D7));
        assertEquals(BD("1.234568E+12"), BigDecimalMath.divide(BD("123456789012"), BD("0.10000000000001"), D7));
    }

    @Test
    public void divide_whenZero() {
        Exception actualException1 = assertThrows(ArithmeticException.class, () ->
                BigDecimalMath.divide(
                        new BigDecimal("1234567"),
                        new BigDecimal("0"),
                        D7
                )
        );
        assertEquals("Division by zero", actualException1.getMessage());

        Exception actualException2 = assertThrows(ArithmeticException.class, () ->
                BigDecimalMath.divide(
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        D7
                )
        );
        assertEquals("Division undefined", actualException2.getMessage());
    }
}