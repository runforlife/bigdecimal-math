package io.github.runforlife.bigdecimal.math;

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
import org.junit.Ignore;
import org.junit.Test;

public class BigDecimalMathToFractionBruteForceTest {
    public static final BigDecimal MAX_DECIMAL_22_DIGITS = new BigDecimal("9999999999999999999999");
    public static final int TOLERANCE = 12;

    private Random random;

    @Before
    public void setUp() {
        random = new Random(19);
    }

    @Test
    public void toFraction_valueIsInteger() {
        BigDecimal integerValue = BigDecimal.TEN;

        BigIntegerFraction actualBigIntegerFraction = BigDecimalMath.toFraction(
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
    public void toFraction_specialCase() {
        BigIntegerFraction actualBigIntegerFraction = BigDecimalMath.toFraction(
                new BigDecimal("0.4578995854774774581478"),
                MAX_DECIMAL_22_DIGITS,
                MAX_DECIMAL_22_DIGITS,
                TOLERANCE,
                MathContext.DECIMAL128
        );

        assertEquals(new BigInteger("51628168192"), actualBigIntegerFraction.getNumerator());
        assertEquals(new BigInteger("112749978007"), actualBigIntegerFraction.getDenominator());
    }

    @Test
    public void toFraction_bruteForceByteNumbers() {
        long max = 384;
        for (int i = 1; i < max; i++) {
            for (int k = 1; k < max; k++) {
                BigDecimal a = BigDecimal.valueOf(i);
                BigDecimal b = BigDecimal.valueOf(k);
                toFraction_assertLite(a, b, k);
            }
        }
    }

    @Test
    public void toFraction_bruteForceLongNumbers() {
        for (int i = 0; i < 2_500_000; i++) {
            BigDecimal a = BigDecimal.valueOf(random.nextLong());
            BigDecimal b = BigDecimal.valueOf(random.nextLong());
            toFraction_assertLite(a, b, i);
        }
    }

    @Test
    public void toFraction_bruteForceBigNumbers() {
        for (int i = 0; i < 5_000_000; i++) {
            BigDecimal a = randomBigDecimalNumberOfDigits(MathContext.DECIMAL128.getPrecision());
            BigDecimal b = randomBigDecimalNumberOfDigits(MathContext.DECIMAL128.getPrecision());
            toFraction_assertLite(a, b, i);
        }
    }

    @Test
    public void toFraction_bruteForce_nextBigDecimal() {
        for (int i = 0; i < 5_000_000; i++) {
            BigDecimal a = randomBigDecimalNumberOfDigits(MathContext.DECIMAL128.getPrecision());
            BigDecimal b = randomBigDecimalNumberOfDigits(MathContext.DECIMAL128.getPrecision());
            toFraction_assertLite(a, b, i);
        }
    }

    @Test
    public void run_toFraction_assertLite() {
        toFraction_assertLite(new BigDecimal("1494924330314517969"), new BigDecimal("2620695565903685639"), -1);
        toFraction_assertLite(new BigDecimal("2321289097316917783435"), new BigDecimal("80466074358344677368918834"), -1);
    }

    void toFraction_assertLite(BigDecimal a, BigDecimal b, int i) {
        MathContext fractionMathContext = new MathContext(29 - TOLERANCE, RoundingMode.HALF_EVEN);

        BigDecimal decimal = a.divide(b, MathContext.DECIMAL128);

        BigDecimal remainderExpected = decimal.remainder(BigDecimal.ONE);
        if (remainderExpected.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        BigIntegerFraction bigIntegerFraction;
        try {
            bigIntegerFraction = BigDecimalMath.toFraction(
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
        BigDecimal decimalActual___ = new BigDecimal(numerator).divide(new BigDecimal(denominator), fractionMathContext);

        String message = "i                      :" + i + "\n" +
                "numerator              :" + numerator + "\n" +
                "denominator            :" + denominator + "\n" +
                "a                      :" + a + "\n" +
                "b                      :" + b + "\n";

        assertEquals(message, decimalExpected, decimalActual___);
        assertTrue("a          : " + a + "\n" + "numerator  : " + numerator, new BigDecimal(numerator).compareTo(MAX_DECIMAL_22_DIGITS) <= 0);
        assertTrue("b          : " + b + "\n" + "denominator: " + denominator, new BigDecimal(denominator).compareTo(MAX_DECIMAL_22_DIGITS) <= 0);
    }

    @Test
    public void toFraction_bug() {
        BigDecimal decimalExpected = new BigDecimal("380.8");

        BigIntegerFraction bigIntegerFraction = BigDecimalMath.toFraction(
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
    public void toFraction_bug_19_332384805645034() {
        BigDecimal a = new BigDecimal("19");
        BigDecimal b = new BigDecimal("332384805645034");
        BigDecimal decimalExpected = a.divide(b, MathContext.DECIMAL128);

        BigIntegerFraction bigIntegerFraction = BigDecimalMath.toFraction(
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
    public void toFraction_bug_8div7mult6() {
        BigDecimal a = new BigDecimal("8");
        BigDecimal b = new BigDecimal("7");
        BigDecimal c = new BigDecimal("6");
        BigDecimal decimalExpected = (a.divide(b, MathContext.DECIMAL128)).multiply(c, MathContext.DECIMAL128);

        BigIntegerFraction bigIntegerFraction = BigDecimalMath.toFraction(
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
    public void toFraction_bruteForce() {
        long max = 1024;
        for (long b = 1; b < max; b++) {
            for (long a = 1; a < max; a++) {
                long commonDenominator = findCommonDenominator(a, b);

                try {
                    BigDecimal expectedBigDecimal = (BigDecimal.valueOf(a)).divide(BigDecimal.valueOf(b), MathContext.DECIMAL128);
                    BigIntegerFraction actulaBigIntegerFraction = BigDecimalMath.toFraction(
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

    private BigDecimal randomBigDecimalNumberOfDigits(int numberOfDigits) {
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