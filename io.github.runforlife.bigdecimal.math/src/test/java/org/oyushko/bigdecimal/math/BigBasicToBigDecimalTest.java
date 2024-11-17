package org.oyushko.bigdecimal.math;

import org.oyushko.bigdecimal.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BigBasicToBigDecimalTest extends AbstractBigDecimalTest {
    @Test
    public void testToBigDecimalFails() {
        assertToBigDecimalThrows("");
        assertToBigDecimalThrows("x");
        assertToBigDecimalThrows(" ");
        assertToBigDecimalThrows("1x");
        assertToBigDecimalThrows("1.2x");
        assertToBigDecimalThrows("1.2E3x");

        assertToBigDecimalThrows("1..2");
        assertToBigDecimalThrows("1.2.3");

        assertToBigDecimalThrows("++1");
        assertToBigDecimalThrows("+1+2");
        assertToBigDecimalThrows("--1");
        assertToBigDecimalThrows("-1-2");
        assertToBigDecimalThrows("+-1");
        assertToBigDecimalThrows("+1-2");
        assertToBigDecimalThrows("-+1");
        assertToBigDecimalThrows("-1+2");

        assertToBigDecimalThrows("1EE2");

        assertToBigDecimalThrows("1E2.3");
        assertToBigDecimalThrows("1E++2");
        assertToBigDecimalThrows("1E+2+3");
        assertToBigDecimalThrows("1E--2");
        assertToBigDecimalThrows("1E-2-3");
        assertToBigDecimalThrows("1E+-2");
        assertToBigDecimalThrows("1E+2-3");
        assertToBigDecimalThrows("1E-+2");
        assertToBigDecimalThrows("1E-2+3");

        assertToBigDecimalThrows("1E9999999999");
        assertToBigDecimalThrows("1E999999999999999");
    }

    @Test
    public void testToBigDecimal() {
        assertToBigDecimal("0");
        assertToBigDecimal("00");
        assertToBigDecimal("0.0");
        assertToBigDecimal("0.00");
        assertToBigDecimal("00.00");
        assertToBigDecimal("+0");
        assertToBigDecimal("-0");
        assertToBigDecimal("+0E123");
        assertToBigDecimal("-0E-123");

        assertToBigDecimal(".123");
        assertToBigDecimal("123.");

        assertToBigDecimal("1");
        assertToBigDecimal("1.0");
        assertToBigDecimal("1.23");
        assertToBigDecimal("1.2300");
        assertToBigDecimal("1234567890");
        assertToBigDecimal("1234567890.1234567890123456789");

        assertToBigDecimal("001");
        assertToBigDecimal("001.23");
        assertToBigDecimal("001.2300");
        assertToBigDecimal("001234567890");
        assertToBigDecimal("001234567890.1234567890123456789");

        assertToBigDecimal("+1");
        assertToBigDecimal("+1.23");
        assertToBigDecimal("+1.2300");
        assertToBigDecimal("+1234567890");
        assertToBigDecimal("+1234567890.1234567890123456789");

        assertToBigDecimal("-1");
        assertToBigDecimal("-1.23");
        assertToBigDecimal("-1.2300");
        assertToBigDecimal("-1234567890");
        assertToBigDecimal("-1234567890.1234567890123456789");

        assertToBigDecimal("1.23E123");
        assertToBigDecimal("1.2300E123");
        assertToBigDecimal("1.23E+123");
        assertToBigDecimal("1.23E-123");
    }

    private static void assertToBigDecimal(String string) {
        BigDecimal expected = BD(string);

        for (int i = 2; i < 10; i++) {
            BigDecimal actual = BigBasic.toBigDecimal(string, MathContext.UNLIMITED, i);

            assertTrue("toBigDecimal(_,_," + i + ") " + expected + " compareTo " + actual, expected.compareTo(actual) == 0);
            assertEquals(expected, actual);
        }
    }

    private static void assertToBigDecimalThrows(String string) {
        assertThrows(NumberFormatException.class, () -> BD(string));

        assertThrows(NumberFormatException.class, () -> BigBasic.toBigDecimal(string, MathContext.UNLIMITED, 1));
    }
}
