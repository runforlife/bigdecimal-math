package io.github.runforlife.bigdecimal.math.rational;

import io.github.runforlife.bigdecimal.math.BigRational;
import io.github.runforlife.bigdecimal.math.util.AbstractBigDecimalTest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static io.github.runforlife.bigdecimal.math.util.ThreadUtil.runMultiThreaded;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class BigRationalValueOfBigDecimalTest extends AbstractBigDecimalTest {
    private static final Object LOCK = new Object();

    private static final BigDecimal MAX_NUMERATOR_DENOMINATOR = new BigDecimal("999999999999999999999");
    //Min epsilon: 1E-45
    private static final BigDecimal DEFAULT_EPSILON = new BigDecimal("1E-5");
    //Max precision: 45
    private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL128;

    //Max count: 67
    private static final int MAX_ITERATIONS = 100;

    private static final int TRY_EPSILON_COUNT = 100;
    private static final int REPEAT_TIMES = 100_000_000;
    private static final int TRY_MATH_CONTEXT_COUNT = 100;

    private Random random;

    @Before
    public void setUp() {
        random = new Random(19);
    }

    @Test
    public void toFraction_cases() {
        boolean success = false;
        MathContext calculationMathContext = DEFAULT_MATH_CONTEXT;

        for (int i = 0; i < TRY_MATH_CONTEXT_COUNT; i++) {
            BigDecimal epsilon = DEFAULT_EPSILON;

            for (int k = 0; k < TRY_EPSILON_COUNT; k++) {
                epsilon = epsilon.divide(BigDecimal.TEN, MathContext.DECIMAL128);
                success = toFraction(
                        new BigDecimal("3323848056450349355"),
                        new BigDecimal("-188465917763093926"),
                        epsilon,
                        calculationMathContext
                );

                if (success) {
                    break;
                }
            }

            if (success) {
                break;
            }

            calculationMathContext = new MathContext(
                    calculationMathContext.getPrecision() + 1,
                    calculationMathContext.getRoundingMode()
            );
        }

        if (!success) {
            fail("fail");
        }
    }

    @Test
    public void valueOf_runMultiThreaded() throws Throwable {
        AtomicBoolean failed = new AtomicBoolean();

        BigRationalValueOfBigDecimalTest thisTest = this;
        AtomicReference<BigDecimal> minEpsilon = new AtomicReference<>(DEFAULT_EPSILON);
        AtomicReference<MathContext> maxMathContext = new AtomicReference<>(MathContext.DECIMAL128);

        runMultiThreaded(() -> {
            for (int i = 0; i < adaptCount(REPEAT_TIMES); i++) {
                if (i % 100_000 == 0) {
                    printDebugMessage(i, minEpsilon.get(), maxMathContext.get());
                }

                MathContext calculationMathContext = maxMathContext.get();

                BigDecimal a = nextBigDecimal(MAX_NUMERATOR_DENOMINATOR.toString().length());
                BigDecimal b = nextBigDecimal(MAX_NUMERATOR_DENOMINATOR.toString().length());

                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }

                BigInteger aBi = a.toBigInteger();
                BigInteger bBi = b.toBigInteger();
                BigInteger gcd = aBi.gcd(bBi);

                aBi = aBi.divide(gcd);
                bBi = bBi.divide(gcd);

                a = new BigDecimal(aBi);
                b = new BigDecimal(bBi);

                boolean found = false;
                for (int j = 0; j < TRY_MATH_CONTEXT_COUNT; j++) {
                    BigDecimal epsilon = minEpsilon.get();

                    for (int k = 0; k < TRY_EPSILON_COUNT; k++) {
                        if (toFraction(a, b, epsilon, calculationMathContext)) {
                            found = true;

                            synchronized (thisTest) {
                                if (minEpsilon.get().compareTo(epsilon) > 0) {
                                    minEpsilon.set(epsilon);
                                }
                                if (maxMathContext.get().getPrecision() < calculationMathContext.getPrecision()) {
                                    maxMathContext.set(calculationMathContext);
                                }
                            }

                            break;
                        }
                        epsilon = epsilon.divide(BigDecimal.TEN, calculationMathContext);
                    }

                    if (found) {
                        break;
                    }

                    calculationMathContext = new MathContext(
                            calculationMathContext.getPrecision() + 1,
                            calculationMathContext.getRoundingMode()
                    );
                }

                if (!found) {
                    failed.set(true);

                    String message = "" +
                            "a: " + a + "\n" +
                            "b: " + b + "\n";

                    synchronized (LOCK) {
                        System.out.println(message);
                    }

                    break;
                }
            }
        });

        printDebugMessage(Integer.MAX_VALUE, minEpsilon.get(), maxMathContext.get());

        assertFalse(failed.get());
    }

    private volatile int lastPrinted = Integer.MIN_VALUE;
    private synchronized void printDebugMessage(int i, BigDecimal minEpsilon, MathContext mathContext) {
        if (i > lastPrinted) {
            lastPrinted = i;
        } else {
            return;
        }

        System.out.println("i: " + i);
        System.out.println("Max count: " + BigRational.MAX_N);
        System.out.println("Min epsilon: " + minEpsilon);
        System.out.println("Max precision: " + mathContext.getPrecision() + "\n");
    }

    boolean toFraction(BigDecimal a, BigDecimal b, BigDecimal epsilon, MathContext calculationMathContext) {
        BigDecimal decimal = a.divide(b, calculationMathContext);

        BigDecimal remainderExpected = decimal.remainder(BigDecimal.ONE);
        if (remainderExpected.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }

        BigRational bigRational;
        try {
            bigRational = BigRational.valueOf(
                    decimal,
                    epsilon,
                    MAX_NUMERATOR_DENOMINATOR,
                    MAX_NUMERATOR_DENOMINATOR,
                    MAX_ITERATIONS,
                    calculationMathContext
            );
        } catch (ArithmeticException e) {
            return false;
        } catch (Exception e) {
            System.out.println("a: " + a + ", b: " + b);
            throw e;
        }

        BigDecimal numerator = bigRational.getNumerator();
        BigDecimal denominator = bigRational.getDenominator();

        BigInteger expectedA = a.toBigInteger();
        BigInteger expectedB = b.toBigInteger();
        BigInteger gcd = expectedA.gcd(expectedB);

        if (gcd.compareTo(BigInteger.ONE) > 0) {
            expectedA = expectedA.divide(gcd);
            expectedB = expectedB.divide(gcd);
        }

        if (expectedB.compareTo(BigInteger.ZERO) < 0) {
            expectedA = expectedA.negate();
            expectedB = expectedB.negate();
        }

        return numerator.toBigInteger().compareTo(expectedA) == 0 && denominator.toBigInteger().compareTo(expectedB) == 0;
    }

    private BigDecimal nextBigDecimal(int numberOfDigits) {
        long value1 = random.nextLong();
        long value2 = Math.abs(random.nextLong());
        String valueAsString = value1 + "" + value2;
        if (valueAsString.contains("-")) {
            if (valueAsString.length() > numberOfDigits + 1) {
                valueAsString = valueAsString.substring(0, numberOfDigits + 1);
            }
        } else {
            if (valueAsString.length() > numberOfDigits) {
                valueAsString = valueAsString.substring(0, numberOfDigits);
            }
        }
        int randomLength = (int) ((numberOfDigits - 1) * random.nextFloat() + 2);
        if (valueAsString.contains("-")) {
            if (randomLength + 1 < valueAsString.length()) {
                valueAsString = valueAsString.substring(0, randomLength + 1);
            }
        } else {
            if (randomLength < valueAsString.length()) {
                valueAsString = valueAsString.substring(0, randomLength);
            }
        }
        return new BigDecimal(valueAsString);
    }
}