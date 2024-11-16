package org.oyushko.big.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BigDecimalFastMathFractionBruteForceTest {
    public static final BigDecimal MAX_DECIMAL_22_DIGITS = new BigDecimal("9999999999999999999999");
    public static final int TOLERANCE = 12;

    private Random random;

    @Before
    public void setUp() {
        random = new Random(19);
    }

    @Test
    public void convertDecimalToFraction_valueIsInteger() {
        BigDecimal integerValue = BigDecimal.TEN;

        BigIntegerFraction actualBigIntegerFraction = BigDecimalMath.convertToFraction(
                integerValue,
                MAX_DECIMAL_22_DIGITS,
                MAX_DECIMAL_22_DIGITS,
                TOLERANCE,
                MathContext.DECIMAL128
        );

        assertEquals(BigInteger.TEN, actualBigIntegerFraction.getNumerator());
        assertEquals(BigInteger.ONE, actualBigIntegerFraction.getDenominator());
    }

    @Test
    public void convertDecimalToFraction_bruteForceByteNumbers() {
        long max = 128;
        for (int i = 1; i < max; i++) {
            for (int k = 1; k < max; k++) {
                BigDecimal a = BigDecimal.valueOf(i);
                BigDecimal b = BigDecimal.valueOf(k);
                convertDecimalToFraction_assertLite(a, b, k);
            }
        }
    }

    @Test
    public void convertDecimalToFraction_bruteForceLongNumbers() {
        for (int i = 0; i < 2500; i++) {
            BigDecimal a = BigDecimal.valueOf(random.nextLong());
            BigDecimal b = BigDecimal.valueOf(random.nextLong());
            convertDecimalToFraction_assertLite(a, b, i);
        }
    }

    @Test
    public void convertDecimalToFraction_bruteForceBigNumbers() {
        for (int i = 0; i < 5000; i++) {
            BigDecimal a = randomBigDecimalNUmberOfDigits(MathContext.DECIMAL128.getPrecision());
            BigDecimal b = randomBigDecimalNUmberOfDigits(MathContext.DECIMAL128.getPrecision());
            convertDecimalToFraction_assertLite(a, b, i);
        }
    }

    void convertDecimalToFraction_assertLite(BigDecimal a, BigDecimal b, int i) {
        MathContext fractionMathContext = new MathContext(29 - TOLERANCE, RoundingMode.HALF_EVEN);

        BigDecimal decimal = a.divide(b, MathContext.DECIMAL128);

        BigDecimal remainderExpected = decimal.remainder(BigDecimal.ONE);
        if (remainderExpected.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        BigIntegerFraction bigIntegerFraction;
        try {
            bigIntegerFraction = BigDecimalMath.convertToFraction(
                    decimal,
                    MAX_DECIMAL_22_DIGITS,
                    MAX_DECIMAL_22_DIGITS,
                    TOLERANCE,
                    MathContext.DECIMAL128
            );
        } catch (ArithmeticException e) {
            return;
        } catch (Exception e) {
            System.out.println("a: " + a + ", b: " + b + ", i: " + i);
            throw e;
        }

        BigInteger numerator = bigIntegerFraction.getNumerator();
        BigInteger denominator = bigIntegerFraction.getDenominator();

        BigDecimal decimalExpected = decimal.round(fractionMathContext);
        BigDecimal decimalActual;
        if (bigIntegerFraction.isPositive()) {
            decimalActual = new BigDecimal(numerator).divide(new BigDecimal(denominator), fractionMathContext);
        } else {
            decimalActual = new BigDecimal(numerator).divide(new BigDecimal(denominator), fractionMathContext).negate(fractionMathContext);
        }

        String message = "i                      :" + i + "\n" +
                "numerator              :" + numerator + "\n" +
                "denominator            :" + denominator + "\n" +
                "a                      :" + a + "\n" +
                "b                      :" + b + "\n";

        assertEquals(message, decimalExpected, decimalActual);
        assertTrue("a          : " + a + "\n" + "numerator  : " + numerator, new BigDecimal(numerator).compareTo(MAX_DECIMAL_22_DIGITS) <= 0);
        assertTrue("b          : " + b + "\n" + "denominator: " + denominator, new BigDecimal(denominator).compareTo(MAX_DECIMAL_22_DIGITS) <= 0);
    }

    @Test
    public void convertDecimalToFraction_bug() {
        BigDecimal decimalExpected = new BigDecimal("380.8");

        BigIntegerFraction bigIntegerFraction = BigDecimalMath.convertToFraction(
                decimalExpected,
                MAX_DECIMAL_22_DIGITS,
                MAX_DECIMAL_22_DIGITS,
                TOLERANCE,
                MathContext.DECIMAL128
        );

        assertEquals(0, new BigInteger("1904").compareTo(bigIntegerFraction.getNumerator()));
        assertEquals(0, new BigInteger("5").compareTo(bigIntegerFraction.getDenominator()));
    }

    @Test
    public void convertDecimalToFraction_19_332384805645034() {
        BigDecimal a = new BigDecimal("19");
        BigDecimal b = new BigDecimal("332384805645034");
        BigDecimal decimalExpected = a.divide(b, MathContext.DECIMAL128);

        BigIntegerFraction bigIntegerFraction = BigDecimalMath.convertToFraction(
                decimalExpected,
                MAX_DECIMAL_22_DIGITS,
                MAX_DECIMAL_22_DIGITS,
                TOLERANCE,
                MathContext.DECIMAL128
        );

        assertEquals(0, new BigInteger("19").compareTo(bigIntegerFraction.getNumerator()));
        assertEquals(0, new BigInteger("332384805645034").compareTo(bigIntegerFraction.getDenominator()));
    }

    @Test
    public void convertDecimalToFraction_8div7mult6() {
        BigDecimal a = new BigDecimal("8");
        BigDecimal b = new BigDecimal("7");
        BigDecimal c = new BigDecimal("6");
        BigDecimal decimalExpected = (a.divide(b, MathContext.DECIMAL128)).multiply(c, MathContext.DECIMAL128);

        BigIntegerFraction bigIntegerFraction = BigDecimalMath.convertToFraction(
                decimalExpected,
                MAX_DECIMAL_22_DIGITS,
                MAX_DECIMAL_22_DIGITS,
                TOLERANCE,
                MathContext.DECIMAL128
        );

        assertEquals(0, new BigInteger("48").compareTo(bigIntegerFraction.getNumerator()));
        assertEquals(0, new BigInteger("7").compareTo(bigIntegerFraction.getDenominator()));
    }

    @Test
    public void convertDecimalToFraction_bruteForce() {
        long max = 128;
        for (long b = 1; b < max; b++) {
            for (long a = 1; a < max; a++) {
                long commonDenominator = findCommonDenominator(a, b);

                try {
                    BigDecimal expectedBigDecimal = (BigDecimal.valueOf(a)).divide(BigDecimal.valueOf(b), MathContext.DECIMAL128);
                    BigIntegerFraction actulaBigIntegerFraction = BigDecimalMath.convertToFraction(
                            expectedBigDecimal,
                            MAX_DECIMAL_22_DIGITS,
                            MAX_DECIMAL_22_DIGITS,
                            TOLERANCE,
                            MathContext.DECIMAL128
                    );

                    BigInteger actualNumerator = actulaBigIntegerFraction.getNumerator();
                    BigInteger actualDenominator = actulaBigIntegerFraction.getDenominator();
                    BigDecimal actualBigDecimal = (new BigDecimal(actualNumerator)).divide(new BigDecimal(actualDenominator), MathContext.DECIMAL128);

                    assertEquals("a: " + a + ", b: " + b + ", c: " + commonDenominator + ", N: " + actualNumerator + ", D: " + actualDenominator,
                            expectedBigDecimal, actualBigDecimal);
                    assertEquals("a: " + a + ", b: " + b + ", c: " + commonDenominator + ", N: " + actualNumerator + ", D: " + actualDenominator,
                            BigInteger.valueOf(a / commonDenominator), actualNumerator);
                    assertEquals("a: " + a + ", b: " + b + ", c: " + commonDenominator + ", N: " + actualNumerator + ", D: " + actualDenominator,
                            BigInteger.valueOf(b / commonDenominator), actualDenominator);
                } catch (Exception e) {
                    fail("a: " + a + ", b: " + b + ", c: " + commonDenominator);
                }
            }
        }
    }

    private BigDecimal nextBigDecimal() {
        int randomScale = (int) (random.nextFloat() * 100 - 50);
        return new BigDecimal(BigInteger.valueOf(random.nextInt()), randomScale);
    }

    private BigDecimal randomBigDecimalNUmberOfDigits(int numberOfDigits) {
        long value1 = random.nextLong();
        long value2 = Math.abs(random.nextLong());
        String valueAsString = value1 + "" + value2;
        if (valueAsString.length() > numberOfDigits) {
            valueAsString = valueAsString.substring(0, numberOfDigits);
        }
        int randomLength = (int) ((numberOfDigits - 1) * random.nextFloat() + 2);
        if (randomLength < valueAsString.length()) {
            valueAsString = valueAsString.substring(0, randomLength);
        }
        return new BigDecimal(valueAsString);
    }

    private long findCommonDenominator(long a, long b) {
        long commonDenominator = 1;
        long maxAB = Math.max(a, b);
        for (long i = 2; i <= maxAB; i++) {
            if (a % i == 0 && b % i == 0) {
                a = a / i;
                b = b / i;
                maxAB = Math.max(a, b);
                commonDenominator = commonDenominator * i;
                i = 1;
            }
        }
        return commonDenominator;
    }
}