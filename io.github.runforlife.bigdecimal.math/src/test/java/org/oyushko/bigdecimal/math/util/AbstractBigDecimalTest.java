package org.oyushko.bigdecimal.math.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractBigDecimalTest {
    public static final MathContext MC = MathContext.DECIMAL128;
    public static final MathContext MC_CHECK_DOUBLE = new MathContext(10);

    public final TestLevel TEST_LEVEL = getTestLevel();
    public final int AUTO_TEST_MAX_PRECISION = getMaxPrecision();
    public final int RANDOM_MAX_PRECISION = getMaxPrecision();

    public enum TestLevel {
        Fast,
        Medium,
        Slow
    }

    public interface Function3<T1, T2, T3, R> {
        R apply(T1 t1, T2 t2, T3 t3);
    }

    public static BigDecimal BD(long value) {
        return new BigDecimal(value);
    }

    public static BigDecimal BD(String value) {
        return new BigDecimal(value);
    }

    public static BigDecimal BD(String value, MathContext mathContext) {
        return new BigDecimal(value, mathContext);
    }

    public void assertPrecisionCalculation(Function<MathContext, BigDecimal> precisionCalculation, int startPrecision, int endPrecision) {
        BigDecimal expected = precisionCalculation.apply(new MathContext(endPrecision * 2));
        //System.out.println("reference expected:      " + expected);
        assertPrecisionCalculation(expected, precisionCalculation, startPrecision, endPrecision);
    }

    public void assertPrecisionCalculation(BigDecimal expected, Function<MathContext, BigDecimal> precisionCalculation, int startPrecision) {
        assertPrecisionCalculation(expected, precisionCalculation, startPrecision, expected.precision() - 20);
    }

    public void assertPrecisionCalculation(BigDecimal expected, Function<MathContext, BigDecimal> precisionCalculation, int startPrecision, int endPrecision) {
        int precision = startPrecision;
        while (precision <= endPrecision) {
            MathContext mathContext = new MathContext(precision);
            System.out.println("Testing precision=" + precision);
            assertBigDecimal(
                    "precision=" + precision,
                    expected.round(mathContext),
                    precisionCalculation.apply(mathContext),
                    mathContext);
            precision += getPrecisionStep();
        }
    }

    public void assertRandomCalculation(int count, String functionName, Function<Random, Double> xFunction, Function<Double, Double> doubleFunction, BiFunction<BigDecimal, MathContext, BigDecimal> calculation) {
        Random random = new Random(1);

        for (int i = 0; i < count; i++) {
            int precision = random.nextInt(RANDOM_MAX_PRECISION) + 1;
            Double xDouble = xFunction.apply(random);
            BigDecimal x = BigDecimal.valueOf(xDouble);

            String description = functionName + "(" + x + ")";

            System.out.println("Testing " + description + " precision=" + precision);
            MathContext mathContext = new MathContext(precision);
            BigDecimal result = calculation.apply(x, mathContext);

            if (doubleFunction != null && precision > MC_CHECK_DOUBLE.getPrecision() + 4) {
                BigDecimal doubleResult = toCheck(doubleFunction.apply(xDouble));
                if (doubleResult != null) {
                    String doubleDescription = description + " vs. double function ";
                    assertBigDecimal(doubleDescription, doubleResult, result, MC_CHECK_DOUBLE);
                }
            }

            MathContext referenceMathContext = new MathContext(precision * 2 + 20);
            BigDecimal referenceResult = calculation.apply(x, referenceMathContext);
            BigDecimal expected = referenceResult.round(mathContext);
            assertBigDecimal(description, expected, result, mathContext);
        }
    }

    public void assertRandomCalculation(int count, String functionName, Function<Random, Double> xFunction, Function<Random, Double> yFunction, BiFunction<Double, Double, Double> doubleFunction, Function3<BigDecimal, BigDecimal, MathContext, BigDecimal> calculation) {
        Random random = new Random(1);

        for (int i = 0; i < count; i++) {
            int precision = random.nextInt(100) + 1;
            Double xDouble = xFunction.apply(random);
            Double yDouble = yFunction.apply(random);

            BigDecimal x = BigDecimal.valueOf(xDouble);
            BigDecimal y = BigDecimal.valueOf(yDouble);

            String description = functionName + "(" + x + "," + y + ")";
            System.out.println("Testing " + description + " precision=" + precision);

            MathContext mathContext = new MathContext(precision);
            BigDecimal result = calculation.apply(x, y, mathContext);

            if (doubleFunction != null && precision > MC_CHECK_DOUBLE.getPrecision() + 4) {
                BigDecimal doubleResult = toCheck(doubleFunction.apply(xDouble, yDouble));
                String doubleDescription = description + " vs. double function : " + result;
                assertBigDecimal(doubleDescription, doubleResult, result, MC_CHECK_DOUBLE);
            }

            BigDecimal expected = calculation.apply(x, y, new MathContext(precision + 20, mathContext.getRoundingMode()));
            assertBigDecimal(description, expected, result, mathContext);
        }
    }

    public void assertRandomCalculation(int count, String function1Name, String function2Name, BiFunction<Random, MathContext, BigDecimal> xFunction, BiFunction<BigDecimal, MathContext, BigDecimal> calculation1, BiFunction<BigDecimal, MathContext, BigDecimal> calculation2) {
        Random random = new Random(1);

        for (int i = 0; i < count; i++) {
            int numberPrecision = random.nextInt(100) + 1;
            int calculationPrecision = numberPrecision + 10;

            MathContext numberMathContext = new MathContext(numberPrecision);
            BigDecimal x = xFunction.apply(random, numberMathContext);

            MathContext calculationMathContext = new MathContext(calculationPrecision);
            BigDecimal y1 = calculation1.apply(x, calculationMathContext);
            BigDecimal y2 = calculation2.apply(x, calculationMathContext);

            String description = "x=" + x + " " + function1Name + "=" + y1 + " " + function2Name + "=" + y2;
            System.out.println("Testing " + description + " precision=" + numberPrecision);

            assertBigDecimal(description, y1, y2, numberMathContext);
        }
    }

    public boolean assertBigDecimal(BigDecimal expected, BigDecimal actual, MathContext mathContext) {
        return assertBigDecimal("", expected, actual, mathContext);
    }

    public boolean assertBigDecimal(String description, BigDecimal expected, BigDecimal actual, MathContext mathContext) {
        MathContext calculationMathContext = new MathContext(mathContext.getPrecision() + 10);
        BigDecimal error = expected.subtract(actual, calculationMathContext).abs();
        BigDecimal acceptableError = actual.round(mathContext).ulp();

        String fullDescription = description + " expected=" + expected + " actual=" + actual + " precision=" + mathContext.getPrecision() + " error=" + error + " acceptableError=" + acceptableError;
        assertTrue(fullDescription, error.compareTo(acceptableError) <= 0);
        return error.signum() == 0;
    }

    public static BigDecimal randomBigDecimal(Random random, MathContext mathContext) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder stringNumber = new StringBuilder();
        stringNumber.append("0.");

        for (int i = 0; i < mathContext.getPrecision(); i++) {
            stringNumber.append(digits[random.nextInt(digits.length)]);
        }

        return BD(stringNumber.toString(), mathContext);
    }

    public static BigDecimal randomBigDecimalWithExponent(Random random, MathContext mathContext) {
        int exponent = random.nextInt(200) - 100;
        return randomBigDecimal(random, mathContext).multiply(BD("1E" + exponent, mathContext), mathContext);
    }

    public static Exception assertThrows(Class<? extends Exception> exceptionClass, Runnable runnable) {
        return assertThrows(exceptionClass, null, runnable);
    }

    public static Exception assertThrows(Class<? extends Exception> exceptionClass, String message, Runnable runnable) {
        Exception result = null;
        try {
            runnable.run();
            fail("Expected: " + exceptionClass.getName());
        } catch (Exception exception) {
            if (!exceptionClass.isAssignableFrom(exception.getClass())) {
                fail("Expected: " + exceptionClass.getName());
            }
            if (message != null && !message.equals(exception.getMessage())) {
                fail("Expected: " + exceptionClass.getName() + " with message: \"" + message + "\" but received message: \"" + exception.getMessage() + "\"");
            }
            result = exception;
        }
        return result;
    }

    public BigDecimal toCheck(double value) {
        long longValue = (long) value;
        if (value == (double) longValue) {
            return BigDecimal.valueOf(longValue);
        }

        if (Double.isFinite(value)) {
            return BigDecimal.valueOf(value);
        }

        return null;
    }

    public BigDecimal toCheck(BigDecimal value) {
        return BigDecimal.valueOf(value.round(MC_CHECK_DOUBLE).doubleValue());
    }

    public int getPrecisionStep() {
        switch (TEST_LEVEL) {
            case Fast:
                return 50;
            case Medium:
                return 20;
            case Slow:
                return 5;
        }
        return 30;
    }

    private int getMaxPrecision() {
        switch (TEST_LEVEL) {
            case Fast:
                return 100;
            case Medium:
                return 200;
            case Slow:
                return 1000;
        }
        return 100;
    }

    public int adaptCount(int count) {
        switch(TEST_LEVEL) {
            case Fast:
                return count;
            case Medium:
                return count * 10;
            case Slow:
                return count * 100;
        }
        return count;
    }

    public double getRangeStep(double step) {
        switch(TEST_LEVEL) {
            case Fast:
                return step;
            case Medium:
                return step / 2;
            case Slow:
                return step / 10;
        }
        return step;
    }

    private TestLevel getTestLevel() {
        TestLevel level = TestLevel.Fast;

        String envTestLevel = System.getenv("BIGDECIMALTEST_LEVEL");
        if (envTestLevel != null) {
            try {
                level = TestLevel.valueOf(envTestLevel);
            } catch (IllegalArgumentException ex) {
                System.err.println("Illegal env var TEST_LEVEL: " + envTestLevel);
            }
        }

        return level;
    }
}
