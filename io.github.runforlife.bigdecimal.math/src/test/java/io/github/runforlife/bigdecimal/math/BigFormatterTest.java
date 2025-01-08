package io.github.runforlife.bigdecimal.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class BigFormatterTest {
    @Test
    public void format_zeroes() {
        assertEquals("0", BigFormatter.format(new BigDecimal("0E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("00E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-00E-2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0", BigFormatter.format(new BigDecimal("0E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("00E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-00E-1"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0", BigFormatter.format(new BigDecimal("0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("00"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-00"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0", BigFormatter.format(new BigDecimal("0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("00E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-00E+0"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0", BigFormatter.format(new BigDecimal("0E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("00E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-00E+1"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0", BigFormatter.format(new BigDecimal("0E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("00E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-00E+2"), 7, RoundingMode.HALF_EVEN));
    }

    @Test
    public void format_one_digit() {
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.0001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.00010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.00001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.000010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.000001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.0000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.000001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.0000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.000001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.0000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.0000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.00000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.00000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.000000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.0001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.00010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.00001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.000010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.000001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.0000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.000001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.0000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.000001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.0000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.0000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.00000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.00000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.000000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.0010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.0001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.00010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.00001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.00001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.00001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.0000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.0000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.00000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.00010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.00001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.00010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.00001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.01", BigFormatter.format(new BigDecimal("1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.10E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.01E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.01"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.01E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.0010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.0001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.00010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.10E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.0010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.0001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.00010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.1", BigFormatter.format(new BigDecimal("10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.1E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.1E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.01E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.0010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.01E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.0010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("1", BigFormatter.format(new BigDecimal("100E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("0100E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("10E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("1E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("01E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("01"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("1.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("01.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("1.E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("1.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("0.1E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("0.10E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("0.01E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1", BigFormatter.format(new BigDecimal("0.010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-100E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-0100E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-10E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1.0E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-01"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-01.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-01.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-0.1E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-0.10E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-0.01E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-0.010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("10", BigFormatter.format(new BigDecimal("1000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("01000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("100E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("0100E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("10E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("10"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("10.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("010.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("10E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("1E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("01E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("0.1E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10", BigFormatter.format(new BigDecimal("0.10E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-1000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-01000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-100E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-0100E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-10E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-10"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-10.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-010.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-10.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-010.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-1E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-01E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-0.1E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-0.10E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("100", BigFormatter.format(new BigDecimal("10000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("010000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("1000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("01000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("100E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("0100E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("100"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("0100"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("100.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("0100.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("100E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("0100E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("10E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("1E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100", BigFormatter.format(new BigDecimal("01E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-10000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-010000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-1000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-01000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-100E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-0100E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-100"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-0100"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-100.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-0100.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-100.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-0100.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-10E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-1E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-01E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("1000", BigFormatter.format(new BigDecimal("100000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("0100000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("10000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("010000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("1000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("01000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("1000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("01000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("1000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("01000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("1000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("01000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("100E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("0100E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("10E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000", BigFormatter.format(new BigDecimal("010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-100000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-0100000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-10000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-010000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-01000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-01000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-01000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-01000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-100E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-0100E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-10E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("10000", BigFormatter.format(new BigDecimal("1000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("01000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("100000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("0100000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("10000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("010000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("10000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("010000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("10000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("010000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("10000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("010000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("1000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("01000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("100E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("10000", BigFormatter.format(new BigDecimal("0100E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-1000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-01000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-100000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-0100000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-10000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-010000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-10000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-010000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-10000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-010000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-10000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-010000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-1000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-01000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-100E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-0100E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("100000", BigFormatter.format(new BigDecimal("10000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("010000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("1000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("01000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("100000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("0100000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("100000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("0100000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("100000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("0100000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("100000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("0100000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("10000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("010000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("1000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("100000", BigFormatter.format(new BigDecimal("01000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-10000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-010000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-1000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-01000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-100000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-0100000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-100000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-0100000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-100000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-0100000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-100000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-0100000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-10000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-010000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-1000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-01000E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("1000000", BigFormatter.format(new BigDecimal("100000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("0100000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("10000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("010000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("1000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("01000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("1000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("01000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("1000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("01000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("1000000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("01000000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("100000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("0100000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("10000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("010000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-100000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-0100000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-10000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-010000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-01000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-01000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-01000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-01000000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-100000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-0100000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-10000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-010000E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("1E+7", BigFormatter.format(new BigDecimal("1000000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("01000000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("100000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("0100000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("010000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("010000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("010000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("010000000E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("1000000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("01000000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("100000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("0100000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-1000000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-01000000000E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-100000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-0100000000E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-010000000E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-010000000"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-010000000.0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-010000000.0E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-1000000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-01000000E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-100000E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-0100000E+2"), 7, RoundingMode.HALF_EVEN));
    }

    @Test
    public void format_two_digit() {
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.00010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.00001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.000010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.000001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.000001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.000001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.00000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.00000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.000000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.0000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.00000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.001E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.000001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.00000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.0000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.001E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.000010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.000001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.00010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.00001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.000010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.010E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.00010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.00001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.000010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.01", BigFormatter.format(new BigDecimal("1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.10E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.01E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.01"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.01E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.0010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.0001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.00010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-1E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-01E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.10E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.001E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.0010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.0001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.00010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("0.1", BigFormatter.format(new BigDecimal("10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.1E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.1E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.01E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.0010E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-10E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-010E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-1E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-01E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.01E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.010E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.001E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.0010E+2"), 7, RoundingMode.HALF_EVEN));

        assertEquals("1.2", BigFormatter.format(new BigDecimal("120E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("0120E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("12E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("012E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("1.2E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("01.2E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("1.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("01.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("1.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("01.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("1.2E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("1.20E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("0.12E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("0.120E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("0.012E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("1.2", BigFormatter.format(new BigDecimal("0.0120E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-120E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-0120E-2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-12E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-012E-1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-1.2E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-01.20E-0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-1.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-01.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-1.2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-01.20"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-1.20E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-01.20E+0"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-0.12E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-0.120E+1"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-0.012E+2"), 7, RoundingMode.HALF_EVEN));
        assertEquals("-1.2", BigFormatter.format(new BigDecimal("-0.0120E+2"), 7, RoundingMode.HALF_EVEN));
    }

//    @Test
//    public void format_negative_0d1_0d01() {
//        assertEquals("-1", BigFormatter.format(new BigDecimal("-1"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1", BigFormatter.format(new BigDecimal("-1E-0"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.1"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-1E-1"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.01"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-1E-2"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-1E-3"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.0001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-1E-4"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.00001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-1E-5"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-1E-6"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E-7", BigFormatter.format(new BigDecimal("-0.0000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-7", BigFormatter.format(new BigDecimal("-1E-7"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E-8", BigFormatter.format(new BigDecimal("-0.00000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-8", BigFormatter.format(new BigDecimal("-1E-8"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E-9", BigFormatter.format(new BigDecimal("-0.000000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-9", BigFormatter.format(new BigDecimal("-1E-9"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E-10", BigFormatter.format(new BigDecimal("-0.0000000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-10", BigFormatter.format(new BigDecimal("-1E-10"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_positive_0d123_0d0123() {
//        assertEquals("0.12346", BigFormatter.format(new BigDecimal("0.1234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.01235", BigFormatter.format(new BigDecimal("0.01234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.00123", BigFormatter.format(new BigDecimal("0.001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-4", BigFormatter.format(new BigDecimal("0.0001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-5", BigFormatter.format(new BigDecimal("0.00001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-6", BigFormatter.format(new BigDecimal("0.000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-7", BigFormatter.format(new BigDecimal("0.0000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-8", BigFormatter.format(new BigDecimal("0.00000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-9", BigFormatter.format(new BigDecimal("0.000000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.2E-10", BigFormatter.format(new BigDecimal("0.0000000001234567890"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_negative_0d123_0d0123() {
//        assertEquals("-0.1235", BigFormatter.format(new BigDecimal("-0.1234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.0123", BigFormatter.format(new BigDecimal("-0.01234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.0012", BigFormatter.format(new BigDecimal("-0.001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-4", BigFormatter.format(new BigDecimal("-0.0001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-5", BigFormatter.format(new BigDecimal("-0.00001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-6", BigFormatter.format(new BigDecimal("-0.000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-7", BigFormatter.format(new BigDecimal("-0.0000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-8", BigFormatter.format(new BigDecimal("-0.00000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-9", BigFormatter.format(new BigDecimal("-0.000000001234567890"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E-10", BigFormatter.format(new BigDecimal("-0.0000000001234567890"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_positive_1_10() {
//        assertEquals("1", BigFormatter.format(new BigDecimal("1"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1", BigFormatter.format(new BigDecimal("1E+0"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("10", BigFormatter.format(new BigDecimal("10"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("10", BigFormatter.format(new BigDecimal("1E+1"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("100", BigFormatter.format(new BigDecimal("100"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("100", BigFormatter.format(new BigDecimal("1E+2"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1000", BigFormatter.format(new BigDecimal("1000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1000", BigFormatter.format(new BigDecimal("1E+3"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("10000", BigFormatter.format(new BigDecimal("10000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("10000", BigFormatter.format(new BigDecimal("1E+4"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("100000", BigFormatter.format(new BigDecimal("100000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("100000", BigFormatter.format(new BigDecimal("1E+5"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1000000", BigFormatter.format(new BigDecimal("1000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1000000", BigFormatter.format(new BigDecimal("1E+6"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+7", BigFormatter.format(new BigDecimal("1+7"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1E+8", BigFormatter.format(new BigDecimal("100000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+8", BigFormatter.format(new BigDecimal("1E+8"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1E+9", BigFormatter.format(new BigDecimal("1000000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+9", BigFormatter.format(new BigDecimal("1E+9"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1E+10", BigFormatter.format(new BigDecimal("10000000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+10", BigFormatter.format(new BigDecimal("1E+10"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_negative_1_10() {
//        assertEquals("-1", BigFormatter.format(new BigDecimal("-1"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1", BigFormatter.format(new BigDecimal("-1E+0"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-10", BigFormatter.format(new BigDecimal("-10"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-10", BigFormatter.format(new BigDecimal("-1E+1"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-100", BigFormatter.format(new BigDecimal("-100"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-100", BigFormatter.format(new BigDecimal("-1E+2"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1E+3"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-10000", BigFormatter.format(new BigDecimal("-10000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-10000", BigFormatter.format(new BigDecimal("-1E+4"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-100000", BigFormatter.format(new BigDecimal("-100000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-100000", BigFormatter.format(new BigDecimal("-1E+5"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1E+6"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-1E+7"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E+8", BigFormatter.format(new BigDecimal("-100000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+8", BigFormatter.format(new BigDecimal("-1E+8"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E+9", BigFormatter.format(new BigDecimal("-1000000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+9", BigFormatter.format(new BigDecimal("-1E+9"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-10000000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-1E+10"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_positive_11_101() {
//        assertEquals("11", BigFormatter.format(new BigDecimal("11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("101", BigFormatter.format(new BigDecimal("101"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1001", BigFormatter.format(new BigDecimal("1001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("10001", BigFormatter.format(new BigDecimal("10001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("100001", BigFormatter.format(new BigDecimal("100001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1000001", BigFormatter.format(new BigDecimal("1000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+8", BigFormatter.format(new BigDecimal("100000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+9", BigFormatter.format(new BigDecimal("1000000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+10", BigFormatter.format(new BigDecimal("10000000001"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_negative_11_101() {
//        assertEquals("-11", BigFormatter.format(new BigDecimal("-11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-101", BigFormatter.format(new BigDecimal("-101"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1001", BigFormatter.format(new BigDecimal("-1001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-10001", BigFormatter.format(new BigDecimal("-10001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-100001", BigFormatter.format(new BigDecimal("-100001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+8", BigFormatter.format(new BigDecimal("-100000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+9", BigFormatter.format(new BigDecimal("-1000000001"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-10000000001"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_positive_11_111() {
//        assertEquals("11", BigFormatter.format(new BigDecimal("11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("111", BigFormatter.format(new BigDecimal("111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1111", BigFormatter.format(new BigDecimal("1111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("11111", BigFormatter.format(new BigDecimal("11111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("111111", BigFormatter.format(new BigDecimal("111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1111111", BigFormatter.format(new BigDecimal("1111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.11E+7", BigFormatter.format(new BigDecimal("11111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.11E+8", BigFormatter.format(new BigDecimal("111111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.11E+9", BigFormatter.format(new BigDecimal("1111111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.1E+10", BigFormatter.format(new BigDecimal("11111111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.1E+99", BigFormatter.format(new BigDecimal("11111111111E+89"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1E+100", BigFormatter.format(new BigDecimal("11111111111E+90"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_negative_11_111() {
//        assertEquals("-11", BigFormatter.format(new BigDecimal("-11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-111", BigFormatter.format(new BigDecimal("-111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1111", BigFormatter.format(new BigDecimal("-1111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-11111", BigFormatter.format(new BigDecimal("-11111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-111111", BigFormatter.format(new BigDecimal("-111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.1E+6", BigFormatter.format(new BigDecimal("-1111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.1E+7", BigFormatter.format(new BigDecimal("-11111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.1E+8", BigFormatter.format(new BigDecimal("-111111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.1E+9", BigFormatter.format(new BigDecimal("-1111111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-11111111111"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+99", BigFormatter.format(new BigDecimal("-11111111111E+89"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+100", BigFormatter.format(new BigDecimal("-11111111111E+90"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_bug1() {
//        assertEquals("0.14286", BigFormatter.format(new BigDecimal("0.1428571428571428571428571428571429"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.1429", BigFormatter.format(new BigDecimal("-0.1428571428571428571428571428571429"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_bug2() {
//        assertEquals("1", BigFormatter.format(new BigDecimal("1.0"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1", BigFormatter.format(new BigDecimal("-1.0"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_bug3() {
//        assertEquals("0", BigFormatter.format(new BigDecimal("0.0"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0", BigFormatter.format(new BigDecimal("0.0000000"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000000"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_bug4() {
//        assertEquals("1.23E+9", BigFormatter.format(new BigDecimal("1234567890.0"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E+9", BigFormatter.format(new BigDecimal("-1234567890.0"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_positive_strange() {
//        assertEquals("1.23E-7", BigFormatter.format(new BigDecimal("123456789E-15"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-6", BigFormatter.format(new BigDecimal("123456789E-14"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-5", BigFormatter.format(new BigDecimal("123456789E-13"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-4", BigFormatter.format(new BigDecimal("123456789E-12"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.00123", BigFormatter.format(new BigDecimal("123456789E-11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.01235", BigFormatter.format(new BigDecimal("123456789E-10"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.12346", BigFormatter.format(new BigDecimal("123456789E-9"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23457", BigFormatter.format(new BigDecimal("123456789E-8"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("12.3457", BigFormatter.format(new BigDecimal("123456789E-7"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("123.457", BigFormatter.format(new BigDecimal("123456789E-6"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1234.57", BigFormatter.format(new BigDecimal("123456789E-5"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("12345.7", BigFormatter.format(new BigDecimal("123456789E-4"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("123457", BigFormatter.format(new BigDecimal("123456789E-3"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1234568", BigFormatter.format(new BigDecimal("123456789E-2"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E+7", BigFormatter.format(new BigDecimal("123456789E-1"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1.23E+8", BigFormatter.format(new BigDecimal("123456789E-0"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("1.23E+7", BigFormatter.format(new BigDecimal("123456789E+1"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1234568", BigFormatter.format(new BigDecimal("123456789E+2"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("123457", BigFormatter.format(new BigDecimal("123456789E+3"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("12345.7", BigFormatter.format(new BigDecimal("123456789E+4"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1234.57", BigFormatter.format(new BigDecimal("123456789E+5"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("123.457", BigFormatter.format(new BigDecimal("123456789E+6"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("12.3457", BigFormatter.format(new BigDecimal("123456789E+7"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23457", BigFormatter.format(new BigDecimal("123456789E+8"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.12346", BigFormatter.format(new BigDecimal("123456789E+9"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.01235", BigFormatter.format(new BigDecimal("123456789E+10"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("0.00123", BigFormatter.format(new BigDecimal("123456789E+11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-4", BigFormatter.format(new BigDecimal("123456789E+12"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-5", BigFormatter.format(new BigDecimal("123456789E+13"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-6", BigFormatter.format(new BigDecimal("123456789E+14"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("1.23E-7", BigFormatter.format(new BigDecimal("123456789E+15"), 7, RoundingMode.HALF_EVEN));
//    }
//
//    @Test
//    public void format_negative_strange() {
//        assertEquals("-1.2E-7", BigFormatter.format(new BigDecimal("-123456789E-15"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-6", BigFormatter.format(new BigDecimal("-123456789E-14"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-5", BigFormatter.format(new BigDecimal("-123456789E-13"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E-4", BigFormatter.format(new BigDecimal("-123456789E-12"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.0012", BigFormatter.format(new BigDecimal("-123456789E-11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.0123", BigFormatter.format(new BigDecimal("-123456789E-10"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-0.1235", BigFormatter.format(new BigDecimal("-123456789E-9"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2346", BigFormatter.format(new BigDecimal("-123456789E-8"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-12.346", BigFormatter.format(new BigDecimal("-123456789E-7"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-123.46", BigFormatter.format(new BigDecimal("-123456789E-6"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1234.6", BigFormatter.format(new BigDecimal("-123456789E-5"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-12346", BigFormatter.format(new BigDecimal("-123456789E-4"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-123457", BigFormatter.format(new BigDecimal("-123456789E-3"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E+6", BigFormatter.format(new BigDecimal("-123456789E-2"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1.2E+7", BigFormatter.format(new BigDecimal("-123456789E-1"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1.2E+8", BigFormatter.format(new BigDecimal("-123456789E-0"), 7, RoundingMode.HALF_EVEN));
//
//        assertEquals("-1.2E+9", BigFormatter.format(new BigDecimal("-123456789E+1"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-123456789E+2"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+11", BigFormatter.format(new BigDecimal("-123456789E+3"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+12", BigFormatter.format(new BigDecimal("-123456789E+4"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+13", BigFormatter.format(new BigDecimal("-123456789E+5"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+14", BigFormatter.format(new BigDecimal("-123456789E+6"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+15", BigFormatter.format(new BigDecimal("-123456789E+7"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+16", BigFormatter.format(new BigDecimal("-123456789E+8"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+17", BigFormatter.format(new BigDecimal("-123456789E+9"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+18", BigFormatter.format(new BigDecimal("-123456789E+10"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+19", BigFormatter.format(new BigDecimal("-123456789E+11"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+20", BigFormatter.format(new BigDecimal("-123456789E+12"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+21", BigFormatter.format(new BigDecimal("-123456789E+13"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+22", BigFormatter.format(new BigDecimal("-123456789E+14"), 7, RoundingMode.HALF_EVEN));
//        assertEquals("-1E+23", BigFormatter.format(new BigDecimal("-123456789E+15"), 7, RoundingMode.HALF_EVEN));
//    }
}