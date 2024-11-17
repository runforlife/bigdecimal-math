package org.oyushko.bigdecimal.math;

import org.oyushko.bigdecimal.math.internal.ExpCalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

public class BigBasic {
    private static final BigDecimal ONE_HALF = valueOf(0.5);
    private static final BigDecimal TWO = valueOf(2);

    private static final Map<Integer, List<BigDecimal>> SPOUGE_FACTORIAL_CONSTANTS_CACHE = new HashMap<>();
    private static final Object SPOUGE_FACTORIAL_CONSTANTS_CACHE_LOCK = new Object();

    static BigDecimal toBigDecimal(String string, MathContext mathContext, int splitLength) {
        int len = string.length();

        if (len < splitLength) {
            return new BigDecimal(string, mathContext);
        }

        char[] chars = string.toCharArray();

        boolean numberHasSign = false;
        boolean negative = false;
        int numberIndex = 0;
        int dotIndex = -1;
        int expIndex = -1;
        boolean expHasSign = false;
        int scale = 0;

        for (int i = 0; i < len; i++) {
            char c = chars[i];
            switch (c) {
                case '+':
                    if (expIndex >= 0) {
                        if (expHasSign) {
                            throw new NumberFormatException("Multiple signs in exponent");
                        }
                        expHasSign = true;
                    } else {
                        if (numberHasSign) {
                            throw new NumberFormatException("Multiple signs in number");
                        }
                        numberHasSign = true;
                        numberIndex = i + 1;
                    }
                    break;
                case '-':
                    if (expIndex >= 0) {
                        if (expHasSign) {
                            throw new NumberFormatException("Multiple signs in exponent");
                        }
                        expHasSign = true;
                    } else {
                        if (numberHasSign) {
                            throw new NumberFormatException("Multiple signs in number");
                        }
                        numberHasSign = true;
                        negative = true;
                        numberIndex = i + 1;
                    }
                    break;
                case 'e':
                case 'E':
                    if (expIndex >= 0) {
                        throw new NumberFormatException("Multiple exponent markers");
                    }
                    expIndex = i;
                    break;
                case '.':
                    if (dotIndex >= 0) {
                        throw new NumberFormatException("Multiple decimal points");
                    }
                    dotIndex = i;
                    break;
                default:
                    if (dotIndex >= 0 && expIndex == -1) {
                        scale++;
                    }
            }
        }

        int numberEndIndex;
        int exp = 0;
        if (expIndex >= 0) {
            numberEndIndex = expIndex;
            String expString = new String(chars, expIndex + 1, len - expIndex - 1);
            exp = Integer.parseInt(expString);
            scale = adjustScale(scale, exp);
        } else {
            numberEndIndex = len;
        }

        BigDecimal result;

        if (dotIndex >= 0) {
            int leftLength = dotIndex - numberIndex;
            BigDecimal bigDecimalLeft = toBigDecimalRecursive(chars, numberIndex, leftLength, exp, splitLength);
            int rightLength = numberEndIndex - dotIndex - 1;
            BigDecimal bigDecimalRight = toBigDecimalRecursive(chars, dotIndex + 1, rightLength, exp-rightLength, splitLength);
            result = bigDecimalLeft.add(bigDecimalRight);
        } else {
            result = toBigDecimalRecursive(chars, numberIndex, numberEndIndex - numberIndex, exp, splitLength);
        }

        if (scale != 0) {
            result = result.setScale(scale);
        }

        if (negative) {
            result = result.negate();
        }

        if (mathContext.getPrecision() != 0) {
            result = BigDecimalMath.round(result, mathContext);
        }

        return result;
    }

    static int adjustScale(int scale, long exp) {
        long adjustedScale = scale - exp;
        if (adjustedScale > Integer.MAX_VALUE || adjustedScale < Integer.MIN_VALUE)
            throw new NumberFormatException("Scale out of range: " + adjustedScale + " while adjusting scale " + scale + " to exponent " + exp);
        return (int) adjustedScale;
    }

    static BigDecimal toBigDecimalRecursive(char[] chars, int offset, int length, int scale, int splitLength) {
        if (length > splitLength) {
            int mid = length / 2;
            BigDecimal bigDecimalLeft = toBigDecimalRecursive(chars, offset, mid, scale + length - mid, splitLength);
            BigDecimal bigDecimalRight = toBigDecimalRecursive(chars, offset + mid, length - mid, scale, splitLength);
            return bigDecimalLeft.add(bigDecimalRight);
        }
        if (length == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(chars, offset, length).movePointRight(scale);
    }

    static List<BigDecimal> getSpougeFactorialConstants(int a) {
        synchronized (SPOUGE_FACTORIAL_CONSTANTS_CACHE_LOCK) {
            return SPOUGE_FACTORIAL_CONSTANTS_CACHE.computeIfAbsent(a, key -> {
                List<BigDecimal> constants = new ArrayList<>(a);
                MathContext mc = new MathContext(a * 15 / 10);

                BigDecimal c0 = BigDecimalMath.sqrt(BigDecimalMath.pi(mc).multiply(TWO, mc), mc);
                constants.add(c0);

                boolean negative = false;
                for (int k = 1; k < a; k++) {
                    BigDecimal bigK = BigDecimal.valueOf(k);
                    long deltaAK = (long)a - k;
                    BigDecimal ck = BigDecimalMath.pow(BigDecimal.valueOf(deltaAK), bigK.subtract(ONE_HALF), mc);
                    ck = ck.multiply(BigDecimalMath.exp(BigDecimal.valueOf(deltaAK), mc), mc);
                    ck = ck.divide(BigDecimalMath.factorial(k - 1), mc);
                    if (negative) {
                        ck = ck.negate();
                    }
                    constants.add(ck);

                    negative = !negative;
                }

                return Collections.unmodifiableList(constants);
            });
        }
    }

    static BigDecimal factorialLoop(int n1, final int n2) {
        final long limit = Long.MAX_VALUE / n2;
        long accu = 1;
        BigDecimal result = BigDecimal.ONE;
        while (n1 <= n2) {
            if (accu <= limit) {
                accu *= n1;
            } else {
                result = result.multiply(BigDecimal.valueOf(accu));
                accu = n1;
            }
            n1++;
        }
        return result.multiply(BigDecimal.valueOf(accu));
    }

    static BigDecimal factorialRecursion(final int n1, final int n2) {
        int threshold = n1 > 200 ? 80 : 150;
        if (n2 - n1 < threshold) {
            return factorialLoop(n1, n2);
        }
        final int mid = (n1 + n2) >> 1;
        return factorialRecursion(mid + 1, n2).multiply(factorialRecursion(n1, mid));
    }

    /**
     * Calculates {@link BigDecimal} x to the power of the integer value y (x<sup>y</sup>).
     *
     * <p>The value y MUST be an integer value.</p>
     *
     * @param x the {@link BigDecimal} value to take to the power
     * @param integerY the {@link BigDecimal} <strong>integer</strong> value to serve as exponent
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated x to the power of y with the precision specified in the <code>mathContext</code>
     * @see BigDecimalMath#pow(BigDecimal, long, MathContext)
     */
    static BigDecimal powInteger(BigDecimal x, BigDecimal integerY, MathContext mathContext) {
        if (BigDecimalMath.fractionalPart(integerY).signum() != 0) {
            throw new IllegalArgumentException("Not integer value: " + integerY);
        }

        if (integerY.signum() < 0) {
            return ONE.divide(powInteger(x, integerY.negate(), mathContext), mathContext);
        }

        MathContext mc = new MathContext(Math.max(mathContext.getPrecision(), -integerY.scale()) + 30, mathContext.getRoundingMode());

        BigDecimal result = ONE;
        while (integerY.signum() > 0) {
            BigDecimal halfY = integerY.divide(TWO, mc);

            if (BigDecimalMath.fractionalPart(halfY).signum() != 0) {
                // odd exponent -> multiply result with x
                result = result.multiply(x, mc);
                integerY = integerY.subtract(ONE);
                halfY = integerY.divide(TWO, mc);
            }

            if (halfY.signum() > 0) {
                // even exponent -> square x
                x = x.multiply(x, mc);
            }

            integerY = halfY;
        }

        return BigDecimalMath.round(result, mathContext);
    }

    static BigDecimal rootUsingNewtonRaphson(BigDecimal x, BigDecimal n, BigDecimal initialResult, MathContext mathContext) {
        if (n.compareTo(BigDecimal.ONE) <= 0) {
            MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
            return BigDecimalMath.pow(x, BigDecimal.ONE.divide(n, mc), mathContext);
        }

        int maxPrecision = mathContext.getPrecision() * 2;
        BigDecimal acceptableError = ONE.movePointLeft(mathContext.getPrecision() + 1);

        BigDecimal nMinus1 = n.subtract(ONE);
        BigDecimal result = initialResult;
        int adaptivePrecision = 12;

        if (adaptivePrecision < maxPrecision) {
            BigDecimal step;
            do {
                adaptivePrecision *= 3;
                if (adaptivePrecision > maxPrecision) {
                    adaptivePrecision = maxPrecision;
                }
                MathContext mc = new MathContext(adaptivePrecision, mathContext.getRoundingMode());

                step = x.divide(BigDecimalMath.pow(result, nMinus1, mc), mc).subtract(result).divide(n, mc);
                result = result.add(step);
            } while (adaptivePrecision < maxPrecision || step.abs().compareTo(acceptableError) > 0);
        }

        return BigDecimalMath.round(result, mathContext);
    }

    static BigDecimal logUsingNewton(BigDecimal x, MathContext mathContext) {
        // https://en.wikipedia.org/wiki/Natural_logarithm in chapter 'High Precision'
        // y = y + 2 * (x-exp(y)) / (x+exp(y))

        int maxPrecision = mathContext.getPrecision() + 20;
        BigDecimal acceptableError = ONE.movePointLeft(mathContext.getPrecision() + 1);
        //System.out.println("logUsingNewton(" + x + " " + mathContext + ") precision " + maxPrecision);

        BigDecimal result;
        int adaptivePrecision;
        double doubleX = x.doubleValue();
        if (doubleX > 0.0 && BigDecimalMath.isDoubleValue(x)) {
            result = BigDecimal.valueOf(Math.log(doubleX));
            adaptivePrecision = BigDecimalMath.EXPECTED_INITIAL_PRECISION;
        } else {
            result = x.divide(TWO, mathContext);
            adaptivePrecision = 1;
        }

        BigDecimal step;

        do {
            adaptivePrecision *= 3;
            if (adaptivePrecision > maxPrecision) {
                adaptivePrecision = maxPrecision;
            }
            MathContext mc = new MathContext(adaptivePrecision, mathContext.getRoundingMode());

            BigDecimal expY = BigDecimalMath.exp(result, mc);
            step = TWO.multiply(x.subtract(expY)).divide(x.add(expY), mc);
            //System.out.println("  step " + step + " adaptivePrecision=" + adaptivePrecision);
            result = result.add(step);
        } while (adaptivePrecision < maxPrecision || step.abs().compareTo(acceptableError) > 0);

        return result;
    }

    private static volatile BigDecimal log2Cache;
    private static final Object log2CacheLock = new Object();

    private static volatile BigDecimal log3Cache;
    private static final Object log3CacheLock = new Object();

    static BigDecimal logTwo(MathContext mathContext) {
        BigDecimal result;

        synchronized (log2CacheLock) {
            if (log2Cache != null && mathContext.getPrecision() <= log2Cache.precision()) {
                result = log2Cache;
            } else {
                log2Cache = logUsingNewton(TWO, mathContext);
                return log2Cache;
            }
        }

        return BigDecimalMath.round(result, mathContext);
    }

    private static final BigDecimal THREE = valueOf(3);

    static BigDecimal logThree(MathContext mathContext) {
        BigDecimal result;

        synchronized (log3CacheLock) {
            if (log3Cache != null && mathContext.getPrecision() <= log3Cache.precision()) {
                result = log3Cache;
            } else {
                log3Cache = logUsingNewton(THREE, mathContext);
                return log3Cache;
            }
        }

        return BigDecimalMath.round(result, mathContext);
    }

    private static volatile BigDecimal log10Cache;
    private static final Object log10CacheLock = new Object();

    static BigDecimal logTen(MathContext mathContext) {
        BigDecimal result;

        synchronized (log10CacheLock) {
            if (log10Cache != null && mathContext.getPrecision() <= log10Cache.precision()) {
                result = log10Cache;
            } else {
                log10Cache = logUsingNewton(BigDecimal.TEN, mathContext);
                return log10Cache;
            }
        }

        return BigDecimalMath.round(result, mathContext);
    }

    static BigDecimal logUsingExponent(BigDecimal x, MathContext mathContext) {
        MathContext mcDouble = new MathContext(mathContext.getPrecision() << 1, mathContext.getRoundingMode());
        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        //System.out.println("logUsingExponent(" + x + " " + mathContext + ") precision " + mc);

        int exponent = BigDecimalMath.exponent(x);
        BigDecimal mantissa = BigDecimalMath.mantissa(x);

        BigDecimal result = logUsingTwoThree(mantissa, mc);
        if (exponent != 0) {
            result = result.add(valueOf(exponent).multiply(logTen(mcDouble), mc));
        }
        return result;
    }

    static BigDecimal logUsingTwoThree(BigDecimal x, MathContext mathContext) {
        MathContext mcDouble = new MathContext(mathContext.getPrecision() << 1, mathContext.getRoundingMode());
        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        //System.out.println("logUsingTwoThree(" + x + " " + mathContext + ") precision " + mc);

        int factorOfTwo = 0;
        int powerOfTwo = 1;
        int factorOfThree = 0;
        int powerOfThree = 1;

        double value = x.doubleValue();
        if (value < 0.01) {
            // do nothing
        } else if (value < 0.1) { // never happens when called by logUsingExponent()
            while (value < 0.6) {
                value *= 2;
                factorOfTwo--;
                powerOfTwo <<= 1;
            }
        }
        else if (value < 0.115) { // (0.1 - 0.11111 - 0.115) -> (0.9 - 1.0 - 1.035)
            factorOfThree = -2;
            powerOfThree = 9;
        }
        else if (value < 0.14) { // (0.115 - 0.125 - 0.14) -> (0.92 - 1.0 - 1.12)
            factorOfTwo = -3;
            powerOfTwo = 8;
        }
        else if (value < 0.2) { // (0.14 - 0.16667 - 0.2) - (0.84 - 1.0 - 1.2)
            factorOfTwo = -1;
            powerOfTwo = 2;
            factorOfThree = -1;
            powerOfThree = 3;
        }
        else if (value < 0.3) { // (0.2 - 0.25 - 0.3) -> (0.8 - 1.0 - 1.2)
            factorOfTwo = -2;
            powerOfTwo = 4;
        }
        else if (value < 0.42) { // (0.3 - 0.33333 - 0.42) -> (0.9 - 1.0 - 1.26)
            factorOfThree = -1;
            powerOfThree = 3;
        }
        else if (value < 0.7) { // (0.42 - 0.5 - 0.7) -> (0.84 - 1.0 - 1.4)
            factorOfTwo = -1;
            powerOfTwo = 2;
        }
        else if (value < 1.4) { // (0.7 - 1.0 - 1.4) -> (0.7 - 1.0 - 1.4)
            // do nothing
        }
        else if (value < 2.5) { // (1.4 - 2.0 - 2.5) -> (0.7 - 1.0 - 1.25)
            factorOfTwo = 1;
            powerOfTwo = 2;
        }
        else if (value < 3.5) { // (2.5 - 3.0 - 3.5) -> (0.833333 - 1.0 - 1.166667)
            factorOfThree = 1;
            powerOfThree = 3;
        }
        else if (value < 5.0) { // (3.5 - 4.0 - 5.0) -> (0.875 - 1.0 - 1.25)
            factorOfTwo = 2;
            powerOfTwo = 4;
        }
        else if (value < 7.0) { // (5.0 - 6.0 - 7.0) -> (0.833333 - 1.0 - 1.166667)
            factorOfThree = 1;
            powerOfThree = 3;
            factorOfTwo = 1;
            powerOfTwo = 2;
        }
        else if (value < 8.5) { // (7.0 - 8.0 - 8.5) -> (0.875 - 1.0 - 1.0625)
            factorOfTwo = 3;
            powerOfTwo = 8;
        }
        else if (value < 10.0) { // (8.5 - 9.0 - 10.0) -> (0.94444 - 1.0 - 1.11111)
            factorOfThree = 2;
            powerOfThree = 9;
        }
        else {
            while (value > 1.4) { // never happens when called by logUsingExponent()
                value /= 2;
                factorOfTwo++;
                powerOfTwo <<= 1;
            }
        }

        BigDecimal correctedX = x;
        BigDecimal result = ZERO;

        if (factorOfTwo > 0) {
            correctedX = correctedX.divide(valueOf(powerOfTwo), mc);
            result = result.add(logTwo(mcDouble).multiply(valueOf(factorOfTwo), mc));
        }
        else if (factorOfTwo < 0) {
            correctedX = correctedX.multiply(valueOf(powerOfTwo), mc);
            result = result.subtract(logTwo(mcDouble).multiply(valueOf(-factorOfTwo), mc));
        }

        if (factorOfThree > 0) {
            correctedX = correctedX.divide(valueOf(powerOfThree), mc);
            result = result.add(logThree(mcDouble).multiply(valueOf(factorOfThree), mc));
        }
        else if (factorOfThree < 0) {
            correctedX = correctedX.multiply(valueOf(powerOfThree), mc);
            result = result.subtract(logThree(mcDouble).multiply(valueOf(-factorOfThree), mc));
        }

        if (x == correctedX && result == ZERO) {
            return logUsingNewton(x, mathContext);
        }

        result = result.add(logUsingNewton(correctedX, mc), mc);

        return result;
    }

    static BigDecimal piChudnovski(MathContext mathContext) {
        MathContext mc = new MathContext(mathContext.getPrecision() + 10, mathContext.getRoundingMode());

        final BigDecimal value24 = BigDecimal.valueOf(24);
        final BigDecimal value640320 = BigDecimal.valueOf(640320);
        final BigDecimal value13591409 = BigDecimal.valueOf(13591409);
        final BigDecimal value545140134 = BigDecimal.valueOf(545140134);
        final BigDecimal valueDivisor = value640320.pow(3).divide(value24, mc);

        BigDecimal sumA = BigDecimal.ONE;
        BigDecimal sumB = BigDecimal.ZERO;

        BigDecimal a = BigDecimal.ONE;
        long dividendTerm1 = 5; // -(6*k - 5)
        long dividendTerm2 = -1; // 2*k - 1
        long dividendTerm3 = -1; // 6*k - 1
        BigDecimal kPower3 = BigDecimal.ZERO;

        long iterationCount = (mc.getPrecision()+13) / 14;
        for (long k = 1; k <= iterationCount; k++) {
            BigDecimal valueK = BigDecimal.valueOf(k);
            dividendTerm1 += -6;
            dividendTerm2 += 2;
            dividendTerm3 += 6;
            BigDecimal dividend = BigDecimal.valueOf(dividendTerm1).multiply(BigDecimal.valueOf(dividendTerm2)).multiply(BigDecimal.valueOf(dividendTerm3));
            kPower3 = valueK.pow(3);
            BigDecimal divisor = kPower3.multiply(valueDivisor, mc);
            a = a.multiply(dividend).divide(divisor, mc);
            BigDecimal b = valueK.multiply(a, mc);

            sumA = sumA.add(a);
            sumB = sumB.add(b);
        }

        final BigDecimal value426880 = BigDecimal.valueOf(426880);
        final BigDecimal value10005 = BigDecimal.valueOf(10005);
        final BigDecimal factor = value426880.multiply(BigDecimalMath.sqrt(value10005, mc));
        BigDecimal pi = factor.divide(value13591409.multiply(sumA, mc).add(value545140134.multiply(sumB, mc)), mc);

        return BigDecimalMath.round(pi, mathContext);
    }

    static BigDecimal expIntegralFractional(BigDecimal x, MathContext mathContext) {
        BigDecimal integralPart = BigDecimalMath.integralPart(x);

        if (integralPart.signum() == 0) {
            return expTaylor(x, mathContext);
        }

        BigDecimal fractionalPart = x.subtract(integralPart);

        MathContext mc = new MathContext(mathContext.getPrecision() + 10, mathContext.getRoundingMode());

        BigDecimal z = ONE.add(fractionalPart.divide(integralPart, mc));
        BigDecimal t = expTaylor(z, mc);

        BigDecimal result = BigDecimalMath.pow(t, integralPart.longValueExact(), mc);

        return BigDecimalMath.round(result, mathContext);
    }

    static BigDecimal expTaylor(BigDecimal x, MathContext mathContext) {
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        x = x.divide(valueOf(256), mc);

        BigDecimal result = ExpCalculator.INSTANCE.calculate(x, mc);
        result = BigDecimalMath.pow(result, 256, mc);
        return BigDecimalMath.round(result, mathContext);
    }

    static void checkMathContext(MathContext mathContext) {
        if (mathContext.getPrecision() == 0) {
            throw new UnsupportedOperationException("Unlimited MathContext not supported");
        }
    }

    private BigBasic() {
        // static utility class
    }
}
