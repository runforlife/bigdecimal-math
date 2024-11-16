package org.oyushko.big.math;

import org.oyushko.big.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class BigDecimalRootPowTest extends AbstractBigDecimalTest {
    @Test
    public void testRootPowRandom() {
        for (BigDecimal n : Arrays.asList(BD("0.1"), BD("1.0"), BD("2.1"), BD("1234.5678"))) {
            assertRandomCalculation(
                    adaptCount(1000),
                    "x",
                    "pow(root(x, " + n + ")," + n + ")",
                    (random, mathContext) -> randomBigDecimal(random, mathContext),
                    (x, mathContext) -> x,
                    (x, mathContext) -> BigDecimalMath.pow(BigDecimalMath.root(x, n, mathContext), n, mathContext));
        }
    }
}
