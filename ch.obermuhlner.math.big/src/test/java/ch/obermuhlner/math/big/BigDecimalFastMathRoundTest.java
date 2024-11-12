package ch.obermuhlner.math.big;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.Test;

public class BigDecimalFastMathRoundTest {
    @Test
    public void round() {
        BigDecimal nearZero74 = new BigDecimal("0.0000000000000000000000000000000000000000000000000000000000000000000000001");
        MathContext mc = new MathContext(68, RoundingMode.HALF_EVEN);

        assertNotEquals(BigDecimal.ZERO, nearZero74.round(mc));
        assertEquals(BigDecimal.ZERO, BigDecimalMath.round2(nearZero74, mc));
    }
}