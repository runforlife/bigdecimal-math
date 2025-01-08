package io.github.runforlife.bigdecimal.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntLongUtilsTest {
    @Test
    public void numberOfDigits_int() {
        assertEquals(1, IntLongUtils.numberOfDigits(0));

        assertEquals(1, IntLongUtils.numberOfDigits(1));
        assertEquals(1, IntLongUtils.numberOfDigits(2));
        assertEquals(1, IntLongUtils.numberOfDigits(3));
        assertEquals(1, IntLongUtils.numberOfDigits(4));
        assertEquals(1, IntLongUtils.numberOfDigits(5));
        assertEquals(1, IntLongUtils.numberOfDigits(6));
        assertEquals(1, IntLongUtils.numberOfDigits(7));
        assertEquals(1, IntLongUtils.numberOfDigits(8));
        assertEquals(1, IntLongUtils.numberOfDigits(9));

        assertEquals(2, IntLongUtils.numberOfDigits(10));
        assertEquals(2, IntLongUtils.numberOfDigits(11));
        assertEquals(2, IntLongUtils.numberOfDigits(98));
        assertEquals(2, IntLongUtils.numberOfDigits(99));

        assertEquals(3, IntLongUtils.numberOfDigits(100));
        assertEquals(3, IntLongUtils.numberOfDigits(101));
        assertEquals(3, IntLongUtils.numberOfDigits(198));
        assertEquals(3, IntLongUtils.numberOfDigits(199));

        assertEquals(4, IntLongUtils.numberOfDigits(1000));
        assertEquals(4, IntLongUtils.numberOfDigits(1001));
        assertEquals(4, IntLongUtils.numberOfDigits(1998));
        assertEquals(4, IntLongUtils.numberOfDigits(1999));

        assertEquals(5, IntLongUtils.numberOfDigits(10000));
        assertEquals(5, IntLongUtils.numberOfDigits(10001));
        assertEquals(5, IntLongUtils.numberOfDigits(19998));
        assertEquals(5, IntLongUtils.numberOfDigits(19999));

        assertEquals(10, IntLongUtils.numberOfDigits(1000000000));
        assertEquals(10, IntLongUtils.numberOfDigits(1000000001));
        assertEquals(10, IntLongUtils.numberOfDigits(1999999998));
        assertEquals(10, IntLongUtils.numberOfDigits(1999999999));


        assertEquals(1, IntLongUtils.numberOfDigits(-1));
        assertEquals(1, IntLongUtils.numberOfDigits(-2));
        assertEquals(1, IntLongUtils.numberOfDigits(-3));
        assertEquals(1, IntLongUtils.numberOfDigits(-4));
        assertEquals(1, IntLongUtils.numberOfDigits(-5));
        assertEquals(1, IntLongUtils.numberOfDigits(-6));
        assertEquals(1, IntLongUtils.numberOfDigits(-7));
        assertEquals(1, IntLongUtils.numberOfDigits(-8));
        assertEquals(1, IntLongUtils.numberOfDigits(-9));

        assertEquals(2, IntLongUtils.numberOfDigits(-10));
        assertEquals(2, IntLongUtils.numberOfDigits(-11));
        assertEquals(2, IntLongUtils.numberOfDigits(-98));
        assertEquals(2, IntLongUtils.numberOfDigits(-99));

        assertEquals(3, IntLongUtils.numberOfDigits(-100));
        assertEquals(3, IntLongUtils.numberOfDigits(-101));
        assertEquals(3, IntLongUtils.numberOfDigits(-198));
        assertEquals(3, IntLongUtils.numberOfDigits(-199));

        assertEquals(4, IntLongUtils.numberOfDigits(-1000));
        assertEquals(4, IntLongUtils.numberOfDigits(-1001));
        assertEquals(4, IntLongUtils.numberOfDigits(-1998));
        assertEquals(4, IntLongUtils.numberOfDigits(-1999));

        assertEquals(5, IntLongUtils.numberOfDigits(-10000));
        assertEquals(5, IntLongUtils.numberOfDigits(-10001));
        assertEquals(5, IntLongUtils.numberOfDigits(-19998));
        assertEquals(5, IntLongUtils.numberOfDigits(-19999));

        assertEquals(10, IntLongUtils.numberOfDigits(-1000000000));
        assertEquals(10, IntLongUtils.numberOfDigits(-1000000001));
        assertEquals(10, IntLongUtils.numberOfDigits(-1999999998));
        assertEquals(10, IntLongUtils.numberOfDigits(-1999999999));
    }

    @Test
    public void numberOfDigits_long() {
        assertEquals(1, IntLongUtils.numberOfDigits(0L));

        assertEquals(1, IntLongUtils.numberOfDigits(1L));
        assertEquals(1, IntLongUtils.numberOfDigits(2L));
        assertEquals(1, IntLongUtils.numberOfDigits(3L));
        assertEquals(1, IntLongUtils.numberOfDigits(4L));
        assertEquals(1, IntLongUtils.numberOfDigits(5L));
        assertEquals(1, IntLongUtils.numberOfDigits(6L));
        assertEquals(1, IntLongUtils.numberOfDigits(7L));
        assertEquals(1, IntLongUtils.numberOfDigits(8L));
        assertEquals(1, IntLongUtils.numberOfDigits(9L));

        assertEquals(2, IntLongUtils.numberOfDigits(10L));
        assertEquals(2, IntLongUtils.numberOfDigits(11L));
        assertEquals(2, IntLongUtils.numberOfDigits(98L));
        assertEquals(2, IntLongUtils.numberOfDigits(99L));

        assertEquals(3, IntLongUtils.numberOfDigits(100L));
        assertEquals(3, IntLongUtils.numberOfDigits(101L));
        assertEquals(3, IntLongUtils.numberOfDigits(198L));
        assertEquals(3, IntLongUtils.numberOfDigits(199L));

        assertEquals(4, IntLongUtils.numberOfDigits(1000L));
        assertEquals(4, IntLongUtils.numberOfDigits(1001L));
        assertEquals(4, IntLongUtils.numberOfDigits(1998L));
        assertEquals(4, IntLongUtils.numberOfDigits(1999L));

        assertEquals(5, IntLongUtils.numberOfDigits(10000L));
        assertEquals(5, IntLongUtils.numberOfDigits(10001L));
        assertEquals(5, IntLongUtils.numberOfDigits(19998L));
        assertEquals(5, IntLongUtils.numberOfDigits(19999L));

        assertEquals(19, IntLongUtils.numberOfDigits(1000000000000000000L));
        assertEquals(19, IntLongUtils.numberOfDigits(1000000000000000001L));
        assertEquals(19, IntLongUtils.numberOfDigits(1999999999999999998L));
        assertEquals(19, IntLongUtils.numberOfDigits(1999999999999999999L));


        assertEquals(1, IntLongUtils.numberOfDigits(-1L));
        assertEquals(1, IntLongUtils.numberOfDigits(-2L));
        assertEquals(1, IntLongUtils.numberOfDigits(-3L));
        assertEquals(1, IntLongUtils.numberOfDigits(-4L));
        assertEquals(1, IntLongUtils.numberOfDigits(-5L));
        assertEquals(1, IntLongUtils.numberOfDigits(-6L));
        assertEquals(1, IntLongUtils.numberOfDigits(-7L));
        assertEquals(1, IntLongUtils.numberOfDigits(-8L));
        assertEquals(1, IntLongUtils.numberOfDigits(-9L));

        assertEquals(2, IntLongUtils.numberOfDigits(-10L));
        assertEquals(2, IntLongUtils.numberOfDigits(-11L));
        assertEquals(2, IntLongUtils.numberOfDigits(-98L));
        assertEquals(2, IntLongUtils.numberOfDigits(-99L));

        assertEquals(3, IntLongUtils.numberOfDigits(-100L));
        assertEquals(3, IntLongUtils.numberOfDigits(-101L));
        assertEquals(3, IntLongUtils.numberOfDigits(-198L));
        assertEquals(3, IntLongUtils.numberOfDigits(-199L));

        assertEquals(4, IntLongUtils.numberOfDigits(-1000L));
        assertEquals(4, IntLongUtils.numberOfDigits(-1001L));
        assertEquals(4, IntLongUtils.numberOfDigits(-1998L));
        assertEquals(4, IntLongUtils.numberOfDigits(-1999L));

        assertEquals(5, IntLongUtils.numberOfDigits(-10000L));
        assertEquals(5, IntLongUtils.numberOfDigits(-10001L));
        assertEquals(5, IntLongUtils.numberOfDigits(-19998L));
        assertEquals(5, IntLongUtils.numberOfDigits(-19999L));

        assertEquals(19, IntLongUtils.numberOfDigits(-1000000000000000000L));
        assertEquals(19, IntLongUtils.numberOfDigits(-1000000000000000001L));
        assertEquals(19, IntLongUtils.numberOfDigits(-1999999999999999998L));
        assertEquals(19, IntLongUtils.numberOfDigits(-1999999999999999999L));
    }
}