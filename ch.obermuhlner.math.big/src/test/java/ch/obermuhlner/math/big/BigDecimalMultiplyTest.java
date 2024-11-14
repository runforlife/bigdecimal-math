package ch.obermuhlner.math.big;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

public class BigDecimalMultiplyTest {
    private static final MathContext DECIMAL_7_DIGITS = MathContext.DECIMAL32;

    @Test
    public void multiply_whenInputAndOutputWithinPrecision() {
        assertEquals(new BigDecimal("1234567"),
                BigDecimalMath.multiply(
                        new BigDecimal("1234567"),
                        new BigDecimal("1"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("-1234567"),
                BigDecimalMath.multiply(
                        new BigDecimal("-1234567"),
                        new BigDecimal("1"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("1234567"),
                BigDecimalMath.multiply(
                        new BigDecimal("-1234567"),
                        new BigDecimal("-1"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("-1234567"),
                BigDecimalMath.multiply(
                        new BigDecimal("1234567"),
                        new BigDecimal("-1"),
                        DECIMAL_7_DIGITS
                )
        );
    }

    @Test
    public void multiply_whenInputIsZero() {
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.multiply(
                        new BigDecimal("1234567"),
                        new BigDecimal("0"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.multiply(
                        new BigDecimal("0"),
                        new BigDecimal("1234567"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.multiply(
                        new BigDecimal("-1234567"),
                        new BigDecimal("0"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.multiply(
                        new BigDecimal("0"),
                        new BigDecimal("-1234567"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("0"),
                BigDecimalMath.multiply(
                        new BigDecimal("0"),
                        new BigDecimal("0"),
                        DECIMAL_7_DIGITS
                )
        );
    }

    @Test
    public void multiply_whenInputOutsideThePrecision() {
        assertEquals(new BigDecimal("1.234568E+7"),
                BigDecimalMath.multiply(
                        new BigDecimal("12345678"),
                        new BigDecimal("1"),
                        DECIMAL_7_DIGITS
                )
        );
        assertEquals(new BigDecimal("1.234568E+7"),
                BigDecimalMath.multiply(
                        new BigDecimal("1"),
                        new BigDecimal("12345678"),
                        DECIMAL_7_DIGITS
                )
        );

        assertEquals(new BigDecimal("1.524158E+14"),
                BigDecimalMath.multiply(
                        new BigDecimal("12345678"),
                        new BigDecimal("12345678"),
                        DECIMAL_7_DIGITS
                )
        );
    }

    @Test
    public void multiply_whenOutputOutsideThePrecision() {
        assertEquals(new BigDecimal("1.592020E+7"),
                BigDecimalMath.multiply(
                        new BigDecimal("2345"),
                        new BigDecimal("6789"),
                        DECIMAL_7_DIGITS
                )
        );
    }
}
