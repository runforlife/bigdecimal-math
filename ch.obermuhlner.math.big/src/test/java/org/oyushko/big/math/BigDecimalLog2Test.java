package org.oyushko.big.math;

import org.oyushko.big.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalLog2Test extends AbstractBigDecimalTest {
    @Test
    public void testLog2Random() {
        assertRandomCalculation(
                adaptCount(100),
                "log",
                random -> random.nextDouble() * 100 + 0.00001,
                (x) -> Math.log(x) / Math.log(2),
                (x, mathContext) -> BigDecimalMath.log2(x, mathContext));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLog2UnlimitedFail() {
        BigDecimalMath.log2(BigDecimal.valueOf(2), MathContext.UNLIMITED);
    }

    //  This test fails for some reason only on travis but not on any other machine I have tested it:
    //    ch.obermuhlner.math.big.BigDecimalMathTest > testLog2PowRandom FAILED
    //        java.lang.AssertionError: x=0.87344468893732422696209498372540635905450777208072365658944016386845896582721178218599 x=0.87344468893732422696209498372540635905450777208072365658944016386845896582721178218599 log2(pow(2,x))=0.873444688937324226962094983725406359054507772080723656589618519811102588824061807610765143887160 expected=0.87344468893732422696209498372540635905450777208072365658944016386845896582721178218599 actual=0.873444688937324226962094983725406359054507772080723656589618519811102588824061807610765143887160 precision=86 error=1.78355942643622996850025424775143887160E-58 acceptableError=1E-86
    //            at org.junit.Assert.fail(Assert.java:88)
    //            at org.junit.Assert.assertTrue(Assert.java:41)
    //            at ch.obermuhlner.math.big.BigDecimalMathTest.assertBigDecimal(BigDecimalMathTest.java:1678)
    //            at ch.obermuhlner.math.big.BigDecimalMathTest.assertRandomCalculation(BigDecimalMathTest.java:1664)
    //            at ch.obermuhlner.math.big.BigDecimalMathTest.testLog2PowRandom(BigDecimalMathTest.java:1542)
    @Test
    public void testLog2PowRandom() {
        assertRandomCalculation(
                adaptCount(1000),
                "x",
                "log2(pow(2,x))",
                (random, mathContext) -> randomBigDecimal(random, mathContext),
                (x, mathContext) -> x,
                (x, mathContext) -> BigDecimalMath.log2(BigDecimalMath.pow(BD(2), x, mathContext), mathContext));
    }

}
