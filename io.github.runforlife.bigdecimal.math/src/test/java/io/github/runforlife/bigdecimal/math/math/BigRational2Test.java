package io.github.runforlife.bigdecimal.math.math;

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

public class BigRational2Test extends AbstractBigDecimalTest {
    private static final Object LOCK = new Object();

    private static final BigInteger MAX_DECIMAL_22_DIGITS = new BigInteger("9999999999999999999999");
    private static final BigDecimal DEFAULT_EPSILON = new BigDecimal("1");
    private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL128;

    private static final int REPEAT_TIMES = 25_000_000;
    private static final int TRY_MATH_CONTEXT_COUNT = 100;
    private static final int TRY_EPSILON_COUNT = 100;

    private Random random;
    private volatile int maxIterations = 10_000;
    private AtomicBoolean failed = new AtomicBoolean();

    @Before
    public void setUp() {
        random = new Random(19);
    }

    @Test
    public void toFraction_cases() {
        boolean success = false;
        MathContext calculationMathContext = DEFAULT_MATH_CONTEXT;
        for (int i = 0; i < adaptCount(REPEAT_TIMES); i++) {
            BigDecimal epsilon = DEFAULT_EPSILON;
            for (int k = 0; k < adaptCount(TRY_EPSILON_COUNT); k++) {
                epsilon = epsilon.divide(BigDecimal.TEN, MathContext.DECIMAL128);
                success = toFraction(
                        new BigDecimal("76234252492721"),
                        new BigDecimal("73548990888724680159"),
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
    public void valueOf() throws Throwable {
        BigRational2Test bigRational2Test = this;
        AtomicReference<BigDecimal> minEpsilon = new AtomicReference<>(DEFAULT_EPSILON);
        AtomicReference<MathContext> maxMathContext = new AtomicReference<>(MathContext.DECIMAL128);

        runMultiThreaded(() -> {
            for (int i = 0; i < adaptCount(REPEAT_TIMES); i++) {
                if (i % 100_000 == 0) {
                    System.out.println("i: " + i);
                }

                MathContext calculationMathContext = maxMathContext.get();

                BigDecimal a = nextBigDecimal(22);
                BigDecimal b = nextBigDecimal(22);

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

                            synchronized (bigRational2Test) {
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

        System.out.println("Max count: " + BigRational.MAX_N);
        System.out.println("Min epsilon: " + minEpsilon.get());
        System.out.println("Max precision: " + maxMathContext.get().getPrecision());

        assertFalse(failed.get());
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
                    MAX_DECIMAL_22_DIGITS,
                    maxIterations,
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

        if (numerator.toBigInteger().compareTo(expectedA) != 0 || denominator.toBigInteger().compareTo(expectedB) != 0) {
            return false;
        }

        return true;
    }

    private BigDecimal nextBigDecimal() {
        int randomScale = (int) (random.nextFloat() * 100 - 50);
        return new BigDecimal(BigInteger.valueOf(random.nextInt()), randomScale);
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