package io.github.runforlife.bigdecimal.math;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BigFormatterTest {
    @Test
    public void format_zero() {
        assertEquals("0", BigFormatter.format(new BigDecimal("0"), 7));
    }

    @Test
    public void format_positive_0d1_0d01() {
        assertEquals("0.1", BigFormatter.format(new BigDecimal("0.10"), 7));
        assertEquals("0.01", BigFormatter.format(new BigDecimal("0.010"), 7));
        assertEquals("0.001", BigFormatter.format(new BigDecimal("0.0010"), 7));
        assertEquals("0.0001", BigFormatter.format(new BigDecimal("0.00010"), 7));
        assertEquals("0.00001", BigFormatter.format(new BigDecimal("0.000010"), 7));
        assertEquals("1E-6", BigFormatter.format(new BigDecimal("0.0000010"), 7));
        assertEquals("1E-7", BigFormatter.format(new BigDecimal("0.00000010"), 7));
        assertEquals("1E-8", BigFormatter.format(new BigDecimal("0.000000010"), 7));
        assertEquals("1E-9", BigFormatter.format(new BigDecimal("0.0000000010"), 7));
        assertEquals("1E-10", BigFormatter.format(new BigDecimal("0.00000000010"), 7));
    }

    @Test
    public void format_negative_0d1_0d01() {
        assertEquals("-0.1", BigFormatter.format(new BigDecimal("-0.10"), 7));
        assertEquals("-0.01", BigFormatter.format(new BigDecimal("-0.010"), 7));
        assertEquals("-0.001", BigFormatter.format(new BigDecimal("-0.0010"), 7));
        assertEquals("-0.0001", BigFormatter.format(new BigDecimal("-0.00010"), 7));
        assertEquals("-1E-5", BigFormatter.format(new BigDecimal("-0.000010"), 7));
        assertEquals("-1E-6", BigFormatter.format(new BigDecimal("-0.0000010"), 7));
        assertEquals("-1E-7", BigFormatter.format(new BigDecimal("-0.00000010"), 7));
        assertEquals("-1E-8", BigFormatter.format(new BigDecimal("-0.000000010"), 7));
        assertEquals("-1E-9", BigFormatter.format(new BigDecimal("-0.0000000010"), 7));
        assertEquals("-1E-10", BigFormatter.format(new BigDecimal("-0.00000000010"), 7));
    }

    @Test
    public void format_positive_0d123_0d0123() {
        assertEquals("0.12346", BigFormatter.format(new BigDecimal("0.1234567890"), 7));
        assertEquals("0.01235", BigFormatter.format(new BigDecimal("0.01234567890"), 7));
        assertEquals("0.00123", BigFormatter.format(new BigDecimal("0.001234567890"), 7));
        assertEquals("1.23E-4", BigFormatter.format(new BigDecimal("0.0001234567890"), 7));
        assertEquals("1.23E-5", BigFormatter.format(new BigDecimal("0.00001234567890"), 7));
        assertEquals("1.23E-6", BigFormatter.format(new BigDecimal("0.000001234567890"), 7));
        assertEquals("1.23E-7", BigFormatter.format(new BigDecimal("0.0000001234567890"), 7));
        assertEquals("1.23E-8", BigFormatter.format(new BigDecimal("0.00000001234567890"), 7));
        assertEquals("1.23E-9", BigFormatter.format(new BigDecimal("0.000000001234567890"), 7));
        assertEquals("1.2E-10", BigFormatter.format(new BigDecimal("0.0000000001234567890"), 7));
    }

    @Test
    public void format_negative_0d123_0d0123() {
        assertEquals("-0.1235", BigFormatter.format(new BigDecimal("-0.1234567890"), 7));
        assertEquals("-0.0123", BigFormatter.format(new BigDecimal("-0.01234567890"), 7));
        assertEquals("-0.0012", BigFormatter.format(new BigDecimal("-0.001234567890"), 7));
        assertEquals("-1.2E-4", BigFormatter.format(new BigDecimal("-0.0001234567890"), 7));
        assertEquals("-1.2E-5", BigFormatter.format(new BigDecimal("-0.00001234567890"), 7));
        assertEquals("-1.2E-6", BigFormatter.format(new BigDecimal("-0.000001234567890"), 7));
        assertEquals("-1.2E-7", BigFormatter.format(new BigDecimal("-0.0000001234567890"), 7));
        assertEquals("-1.2E-8", BigFormatter.format(new BigDecimal("-0.00000001234567890"), 7));
        assertEquals("-1.2E-9", BigFormatter.format(new BigDecimal("-0.000000001234567890"), 7));
        assertEquals("-1E-10", BigFormatter.format(new BigDecimal("-0.0000000001234567890"), 7));
    }

    @Test
    public void format_positive_1_10() {
        assertEquals("1", BigFormatter.format(new BigDecimal("1"), 7));
        assertEquals("10", BigFormatter.format(new BigDecimal("10"), 7));
        assertEquals("100", BigFormatter.format(new BigDecimal("100"), 7));
        assertEquals("1000", BigFormatter.format(new BigDecimal("1000"), 7));
        assertEquals("10000", BigFormatter.format(new BigDecimal("10000"), 7));
        assertEquals("100000", BigFormatter.format(new BigDecimal("100000"), 7));
        assertEquals("1000000", BigFormatter.format(new BigDecimal("1000000"), 7));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000000"), 7));
        assertEquals("1E+8", BigFormatter.format(new BigDecimal("100000000"), 7));
        assertEquals("1E+9", BigFormatter.format(new BigDecimal("1000000000"), 7));
        assertEquals("1E+10", BigFormatter.format(new BigDecimal("10000000000"), 7));
    }

    @Test
    public void format_negative_1_10() {
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1"), 7));
        assertEquals("-10", BigFormatter.format(new BigDecimal("-10"), 7));
        assertEquals("-100", BigFormatter.format(new BigDecimal("-100"), 7));
        assertEquals("-1000", BigFormatter.format(new BigDecimal("-1000"), 7));
        assertEquals("-10000", BigFormatter.format(new BigDecimal("-10000"), 7));
        assertEquals("-100000", BigFormatter.format(new BigDecimal("-100000"), 7));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000000"), 7));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000000"), 7));
        assertEquals("-1E+8", BigFormatter.format(new BigDecimal("-100000000"), 7));
        assertEquals("-1E+9", BigFormatter.format(new BigDecimal("-1000000000"), 7));
        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-10000000000"), 7));
    }

    @Test
    public void format_positive_11_101() {
        assertEquals("11", BigFormatter.format(new BigDecimal("11"), 7));
        assertEquals("101", BigFormatter.format(new BigDecimal("101"), 7));
        assertEquals("1001", BigFormatter.format(new BigDecimal("1001"), 7));
        assertEquals("10001", BigFormatter.format(new BigDecimal("10001"), 7));
        assertEquals("100001", BigFormatter.format(new BigDecimal("100001"), 7));
        assertEquals("1000001", BigFormatter.format(new BigDecimal("1000001"), 7));
        assertEquals("1E+7", BigFormatter.format(new BigDecimal("10000001"), 7));
        assertEquals("1E+8", BigFormatter.format(new BigDecimal("100000001"), 7));
        assertEquals("1E+9", BigFormatter.format(new BigDecimal("1000000001"), 7));
        assertEquals("1E+10", BigFormatter.format(new BigDecimal("10000000001"), 7));
    }

    @Test
    public void format_negative_11_101() {
        assertEquals("-11", BigFormatter.format(new BigDecimal("-11"), 7));
        assertEquals("-101", BigFormatter.format(new BigDecimal("-101"), 7));
        assertEquals("-1001", BigFormatter.format(new BigDecimal("-1001"), 7));
        assertEquals("-10001", BigFormatter.format(new BigDecimal("-10001"), 7));
        assertEquals("-100001", BigFormatter.format(new BigDecimal("-100001"), 7));
        assertEquals("-1E+6", BigFormatter.format(new BigDecimal("-1000001"), 7));
        assertEquals("-1E+7", BigFormatter.format(new BigDecimal("-10000001"), 7));
        assertEquals("-1E+8", BigFormatter.format(new BigDecimal("-100000001"), 7));
        assertEquals("-1E+9", BigFormatter.format(new BigDecimal("-1000000001"), 7));
        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-10000000001"), 7));
    }

    @Test
    public void format_positive_11_111() {
        assertEquals("11", BigFormatter.format(new BigDecimal("11"), 7));
        assertEquals("111", BigFormatter.format(new BigDecimal("111"), 7));
        assertEquals("1111", BigFormatter.format(new BigDecimal("1111"), 7));
        assertEquals("11111", BigFormatter.format(new BigDecimal("11111"), 7));
        assertEquals("111111", BigFormatter.format(new BigDecimal("111111"), 7));
        assertEquals("1111111", BigFormatter.format(new BigDecimal("1111111"), 7));
        assertEquals("1.11E+7", BigFormatter.format(new BigDecimal("11111111"), 7));
        assertEquals("1.11E+8", BigFormatter.format(new BigDecimal("111111111"), 7));
        assertEquals("1.11E+9", BigFormatter.format(new BigDecimal("1111111111"), 7));
        assertEquals("1.1E+10", BigFormatter.format(new BigDecimal("11111111111"), 7));
        assertEquals("1.1E+99", BigFormatter.format(new BigDecimal("11111111111E+89"), 7));
        assertEquals("1E+100", BigFormatter.format(new BigDecimal("11111111111E+90"), 7));
    }

    @Test
    public void format_negative_11_111() {
        assertEquals("-11", BigFormatter.format(new BigDecimal("-11"), 7));
        assertEquals("-111", BigFormatter.format(new BigDecimal("-111"), 7));
        assertEquals("-1111", BigFormatter.format(new BigDecimal("-1111"), 7));
        assertEquals("-11111", BigFormatter.format(new BigDecimal("-11111"), 7));
        assertEquals("-111111", BigFormatter.format(new BigDecimal("-111111"), 7));
        assertEquals("-1.1E+6", BigFormatter.format(new BigDecimal("-1111111"), 7));
        assertEquals("-1.1E+7", BigFormatter.format(new BigDecimal("-11111111"), 7));
        assertEquals("-1.1E+8", BigFormatter.format(new BigDecimal("-111111111"), 7));
        assertEquals("-1.1E+9", BigFormatter.format(new BigDecimal("-1111111111"), 7));
        assertEquals("-1E+10", BigFormatter.format(new BigDecimal("-11111111111"), 7));
        assertEquals("-1E+99", BigFormatter.format(new BigDecimal("-11111111111E+89"), 7));
        assertEquals("-1E+100", BigFormatter.format(new BigDecimal("-11111111111E+90"), 7));
    }

    @Test
    public void format_bug1() {
        assertEquals("0.14286", BigFormatter.format(new BigDecimal("0.1428571428571428571428571428571429"), 7));
        assertEquals("-0.1429", BigFormatter.format(new BigDecimal("-0.1428571428571428571428571428571429"), 7));
    }

    @Test
    public void format_bug2() {
        assertEquals("1", BigFormatter.format(new BigDecimal("1.0"), 7));
        assertEquals("-1", BigFormatter.format(new BigDecimal("-1.0"), 7));
    }

    @Test
    public void format_bug3() {
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0"), 7));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0"), 7));
        assertEquals("0", BigFormatter.format(new BigDecimal("0.0000000"), 7));
        assertEquals("0", BigFormatter.format(new BigDecimal("-0.0000000"), 7));
    }

    @Test
    public void format_bug4() {
        assertEquals("1.24E+9", BigFormatter.format(new BigDecimal("1234567890.0"), 7));
        assertEquals("-1.2E+9", BigFormatter.format(new BigDecimal("-1234567890.0"), 7));
    }
}