package org.oyushko.big.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.valueOf;

public class BigBasic {
    private static final BigDecimal ONE_HALF = valueOf(0.5);
    private static final BigDecimal TWO = valueOf(2);

    private static final int ARITHMETIC_RESERVE_PRECISION = 5;

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

                BigDecimal c0 = BigDecimalMath.sqrt(BigDecimalMath.multiply(BigDecimalMath.pi(mc), TWO, mc), mc);
                constants.add(c0);

                boolean negative = false;
                for (int k = 1; k < a; k++) {
                    BigDecimal bigK = BigDecimal.valueOf(k);
                    long deltaAK = (long)a - k;
                    BigDecimal ck = BigDecimalMath.pow(BigDecimal.valueOf(deltaAK), BigDecimalMath.subtract(bigK, ONE_HALF, mc), mc);
                    ck = BigDecimalMath.multiply(ck, BigDecimalMath.exp(BigDecimal.valueOf(deltaAK), mc), mc);
                    ck = BigDecimalMath.divide(ck, BigDecimalMath.factorial(k - 1), mc);
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

    private static volatile long MATH_CONTEXT_WITH_HALF_EVEN_PRECISION = -1;
    private static volatile MathContext MATH_CONTEXT_WITH_HALF_EVEN_CACHE = null;
    private static final Object MATH_CONTEXT_WITH_HALF_EVEN_CACHE_LOCK = new Object();

    static MathContext buildArithmeticMathContext(int precision) {
        synchronized (MATH_CONTEXT_WITH_HALF_EVEN_CACHE_LOCK) {
            int arithmeticPrecision = precision + ARITHMETIC_RESERVE_PRECISION;
            if (arithmeticPrecision == MATH_CONTEXT_WITH_HALF_EVEN_PRECISION) {
                return MATH_CONTEXT_WITH_HALF_EVEN_CACHE;
            } else {
                MathContext mc = new MathContext(arithmeticPrecision, RoundingMode.HALF_EVEN);
                MATH_CONTEXT_WITH_HALF_EVEN_PRECISION = arithmeticPrecision;
                MATH_CONTEXT_WITH_HALF_EVEN_CACHE = mc;
                return mc;
            }
        }
    }

    private BigBasic() {
        // static utility class
    }
}
