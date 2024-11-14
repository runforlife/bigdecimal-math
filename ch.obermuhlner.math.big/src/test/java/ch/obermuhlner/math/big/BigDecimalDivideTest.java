package ch.obermuhlner.math.big;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static ch.obermuhlner.math.big.BigDecimalMathTest.assertThrows;
import static org.junit.Assert.assertEquals;

public class BigDecimalDivideTest {
    private static final MathContext DECIMAL_7_DIGITS = MathContext.DECIMAL32;

    @Test
    public void divide_whenInputAndOutputWithinPrecision() {
        assertEquals(new BigDecimal("617283.5"),
                BigDecimalMath.divide(
                        new BigDecimal("1234567"),
                        new BigDecimal("2"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("-617283.5"),
                BigDecimalMath.divide(
                        new BigDecimal("-1234567"),
                        new BigDecimal("2"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("1234567"),
                BigDecimalMath.divide(
                        new BigDecimal("-1234567"),
                        new BigDecimal("-1"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("-1234567"),
                BigDecimalMath.divide(
                        new BigDecimal("1234567"),
                        new BigDecimal("-1"),
                        DECIMAL_7_DIGITS
                )
        );
    }

    @Test
    public void divide_whenDividendIsZero() {
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.divide(
                        new BigDecimal("0"),
                        new BigDecimal("1234567"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.divide(
                        new BigDecimal("0"),
                        new BigDecimal("-1234567"),
                        DECIMAL_7_DIGITS
                )
        );
    }

    @Test
    public void divide_whenDivisorIsZero() {
        Exception actualException1 = assertThrows(ArithmeticException.class, () ->
                BigDecimalMath.divide(
                        new BigDecimal("1234567"),
                        new BigDecimal("0"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals("Division by zero", actualException1.getMessage());

        Exception actualException2 = assertThrows(ArithmeticException.class, () ->
                BigDecimalMath.divide(
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals("Division undefined", actualException2.getMessage());
    }

    @Test
    public void divide_whenInputOutsideThePrecision() {
        assertEquals(new BigDecimal("6.17284E+6"),
                BigDecimalMath.divide(
                        new BigDecimal("12345678"),
                        new BigDecimal("2"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("1.620000E-7"),
                BigDecimalMath.divide(
                        new BigDecimal("2"),
                        new BigDecimal("12345678"),
                        DECIMAL_7_DIGITS
                )
        );

        assertEquals(new BigDecimal("1"),
                BigDecimalMath.divide(
                        new BigDecimal("12345678"),
                        new BigDecimal("12345678"),
                        DECIMAL_7_DIGITS
                )
        );
    }

    @Test
    public void divide_whenOutputOutsideThePrecision() {
        assertEquals(new BigDecimal("3.454117E+7"),
                BigDecimalMath.divide(
                        new BigDecimal("2345"),
                        new BigDecimal("0.6789E-4"),
                        DECIMAL_7_DIGITS
                )
        );
    }
}
