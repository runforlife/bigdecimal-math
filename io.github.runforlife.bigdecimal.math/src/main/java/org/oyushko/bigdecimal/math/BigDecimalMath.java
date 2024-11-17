package org.oyushko.bigdecimal.math;

import org.oyushko.bigdecimal.math.internal.AsinCalculator;
import org.oyushko.bigdecimal.math.internal.CosCalculator;
import org.oyushko.bigdecimal.math.internal.CoshCalculator;
import org.oyushko.bigdecimal.math.internal.SinCalculator;
import org.oyushko.bigdecimal.math.internal.SinhCalculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

/**
 * Provides advanced functions operating on {@link BigDecimal}s.
 */
public class BigDecimalMath {
    private static final BigDecimal TWO = valueOf(2);
    private static final BigDecimal MINUS_ONE = valueOf(-1);
    private static final BigDecimal ONE_HALF = valueOf(0.5);
    private static final BigDecimal ONE_HUNDRED_EIGHTY = valueOf(180);
    private static final BigDecimal DOUBLE_MAX_VALUE = BigDecimal.valueOf(Double.MAX_VALUE);
    private static final BigDecimal ROUGHLY_TWO_PI = new BigDecimal("3.141592653589793").multiply(TWO);

    static final int EXPECTED_INITIAL_PRECISION = 15;

    /**
     * Creates a {@link BigDecimal} from the specified <code>String</code> representation.
     *
     * <p>This method is equivalent to the String constructor {@link BigDecimal#BigDecimal(String)}
     * but has been optimized for large strings (several thousand digits).</p>
     *
     * @param string the String representation
     * @return the created {@link BigDecimal}
     * @throws NumberFormatException if <code>string</code> is not a valid representation of a {@link BigDecimal}
     * @see BigDecimal#BigDecimal(String)
     * @see #toBigDecimal(String, MathContext)
     */
    public static BigDecimal toBigDecimal(String string) {
        return toBigDecimal(string, MathContext.UNLIMITED);
    }

    /**
     * Creates a {@link BigDecimal} from the specified <code>String</code> representation.
     *
     * <p>This method is equivalent to the String constructor {@link BigDecimal#BigDecimal(String, MathContext)}
     * but has been optimized for large strings (several thousand digits).</p>
     *
     * @param string      the string representation
     * @param mathContext the {@link MathContext} used for the result
     * @return the created {@link BigDecimal}
     * @throws NumberFormatException if <code>string</code> is not a valid representation of a {@link BigDecimal}
     * @throws ArithmeticException   if the result is inexact but the rounding mode is {@code UNNECESSARY}
     * @see BigDecimal#BigDecimal(String, MathContext)
     * @see #toBigDecimal(String)
     */
    public static BigDecimal toBigDecimal(String string, MathContext mathContext) {
        int len = string.length();
        if (len < 600) {
            return new BigDecimal(string, mathContext);
        }

        int splitLength = len / (len >= 10000 ? 8 : 5);
        return BigBasic.toBigDecimal(string, mathContext, splitLength);
    }

    /**
     * Returns whether the specified {@link BigDecimal} value can be represented as <code>int</code>.
     *
     * <p>If this returns <code>true</code> you can call {@link BigDecimal#intValueExact()} without fear of an {@link ArithmeticException}.</p>
     *
     * @param value the {@link BigDecimal} to check
     * @return <code>true</code> if the value can be represented as <code>int</code> value
     */
    public static boolean isIntValue(BigDecimal value) {
        // TODO impl isIntValue() without exceptions
        try {
            value.intValueExact();
            return true;
        } catch (ArithmeticException ex) {
            // ignored
        }
        return false;
    }

    /**
     * Returns whether the specified {@link BigDecimal} value can be represented as <code>long</code>.
     *
     * <p>If this returns <code>true</code> you can call {@link BigDecimal#longValueExact()} without fear of an {@link ArithmeticException}.</p>
     *
     * @param value the {@link BigDecimal} to check
     * @return <code>true</code> if the value can be represented as <code>long</code> value
     */
    public static boolean isLongValue(BigDecimal value) {
        // TODO impl isLongValue() without exceptions
        try {
            value.longValueExact();
            return true;
        } catch (ArithmeticException ex) {
            // ignored
        }
        return false;
    }

    /**
     * Returns whether the specified {@link BigDecimal} value can be represented as <code>double</code>.
     *
     * <p>If this returns <code>true</code> you can call {@link BigDecimal#doubleValue()}
     * without fear of getting {@link Double#POSITIVE_INFINITY} or {@link Double#NEGATIVE_INFINITY} as result.</p>
     *
     * <p>Example: <code>BigDecimalMath.isDoubleValue(new BigDecimal("1E309"))</code> returns <code>false</code>,
     * because <code>new BigDecimal("1E309").doubleValue()</code> returns <code>Infinity</code>.</p>
     *
     * <p>Note: This method does <strong>not</strong> check for possible loss of precision.</p>
     *
     * <p>For example <code>BigDecimalMath.isDoubleValue(new BigDecimal("1.23400000000000000000000000000000001"))</code> will return <code>true</code>,
     * because <code>new BigDecimal("1.23400000000000000000000000000000001").doubleValue()</code> returns a valid double value,
     * although it loses precision and returns <code>1.234</code>.</p>
     *
     * <p><code>BigDecimalMath.isDoubleValue(new BigDecimal("1E-325"))</code> will return <code>true</code>
     * although this value is smaller than {@link Double#MIN_VALUE} (and therefore outside the range of values that can be represented as <code>double</code>)
     * because <code>new BigDecimal("1E-325").doubleValue()</code> returns <code>0</code> which is a legal value with loss of precision.</p>
     *
     * @param value the {@link BigDecimal} to check
     * @return <code>true</code> if the value can be represented as <code>double</code> value
     */
    public static boolean isDoubleValue(BigDecimal value) {
        if (value.compareTo(DOUBLE_MAX_VALUE) > 0) {
            return false;
        }
        if (value.compareTo(DOUBLE_MAX_VALUE.negate()) < 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns the mantissa of the specified {@link BigDecimal} written as <em>mantissa * 10<sup>exponent</sup></em>.
     *
     * <p>The mantissa is defined as having exactly 1 digit before the decimal point.</p>
     *
     * @param value the {@link BigDecimal}
     * @return the mantissa
     * @see #exponent(BigDecimal)
     */
    public static BigDecimal mantissa(BigDecimal value) {
        int exponent = exponent(value);
        if (exponent == 0) {
            return value;
        }

        return value.movePointLeft(exponent);
    }

    /**
     * Returns the exponent of the specified {@link BigDecimal} written as <em>mantissa * 10<sup>exponent</sup></em>.
     *
     * <p>The mantissa is defined as having exactly 1 digit before the decimal point.</p>
     *
     * @param value the {@link BigDecimal}
     * @return the exponent
     * @see #mantissa(BigDecimal)
     */
    public static int exponent(BigDecimal value) {
        return value.precision() - value.scale() - 1;
    }

    /**
     * Returns the number of significant digits of the specified {@link BigDecimal}.
     *
     * <p>The result contains the number of all digits before the decimal point and
     * all digits after the decimal point excluding trailing zeroes.</p>
     *
     * <p>Examples:</p>
     * <ul>
     * <li><code>significantDigits(new BigDecimal("12300.00"))</code> returns 5</li>
     * <li><code>significantDigits(new BigDecimal("1.23000"))</code> returns 3</li>
     * <li><code>significantDigits(new BigDecimal("0.00012300"))</code> returns 3</li>
     * <li><code>significantDigits(new BigDecimal("12300.4500"))</code> returns 7</li>
     * </ul>
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Significant_figures">Wikipedia: Significant figures</a></p>
     *
     * @param value the {@link BigDecimal}
     * @return the number of significant digits
     * @see BigDecimal#stripTrailingZeros()
     * @see BigDecimal#precision()
     */
    public static int significantDigits(BigDecimal value) {
        BigDecimal stripped = value.stripTrailingZeros();
        if (stripped.scale() >= 0) {
            return stripped.precision();
        } else {
            return stripped.precision() - stripped.scale();
        }
    }

    /**
     * Returns the integral part of the specified {@link BigDecimal} (left of the decimal point).
     *
     * @param value the {@link BigDecimal}
     * @return the integral part
     * @see #fractionalPart(BigDecimal)
     */
    public static BigDecimal integralPart(BigDecimal value) {
        return value.setScale(0, BigDecimal.ROUND_DOWN);
    }

    /**
     * Returns the fractional part of the specified {@link BigDecimal} (right of the decimal point).
     *
     * @param value the {@link BigDecimal}
     * @return the fractional part
     * @see #integralPart(BigDecimal)
     */
    public static BigDecimal fractionalPart(BigDecimal value) {
        return value.subtract(integralPart(value));
    }

    /**
     * Rounds the specified {@link BigDecimal} to the precision of the specified {@link MathContext}.
     *
     * <p>This method calls {@link BigDecimal#round(MathContext)}.</p>
     *
     * @param value       the {@link BigDecimal} to round
     * @param mathContext the {@link MathContext} used for the result
     * @return the rounded {@link BigDecimal} value
     * @see BigDecimal#round(MathContext)
     * @see BigDecimalMath#roundWithTrailingZeroes(BigDecimal, MathContext)
     */
    public static BigDecimal round(BigDecimal value, MathContext mathContext) {
        return value.round(mathContext);
    }

    /**
     * Rounds the specified {@link BigDecimal} to the precision of the specified {@link MathContext} including trailing zeroes.
     *
     * <p>This method is similar to {@link BigDecimal#round(MathContext)} but does <strong>not</strong> remove the trailing zeroes.</p>
     *
     * <p>Example:</p>
     * <pre>
     * MathContext mc = new MathContext(5);
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("1.234567"), mc));    // 1.2346
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("123.4567"), mc));    // 123.46
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("0.001234567"), mc)); // 0.0012346
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("1.23"), mc));        // 1.2300
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("1.230000"), mc));    // 1.2300
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("0.00123"), mc));     // 0.0012300
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("0"), mc));           // 0.0000
     * System.out.println(BigDecimalMath.roundWithTrailingZeroes(new BigDecimal("0.00000000"), mc));  // 0.0000
     * </pre>
     *
     * @param value       the {@link BigDecimal} to round
     * @param mathContext the {@link MathContext} used for the result
     * @return the rounded {@link BigDecimal} value including trailing zeroes
     * @see BigDecimal#round(MathContext)
     * @see BigDecimalMath#round(BigDecimal, MathContext)
     */
    public static BigDecimal roundWithTrailingZeroes(BigDecimal value, MathContext mathContext) {
        if (value.precision() == mathContext.getPrecision()) {
            return value;
        }
        if (value.signum() == 0) {
            return BigDecimal.ZERO.setScale(mathContext.getPrecision() - 1);
        }

        try {
            BigDecimal stripped = value.stripTrailingZeros();
            int exponentStripped = exponent(stripped); // value.precision() - value.scale() - 1;

            BigDecimal zero;
            if (exponentStripped < -1) {
                zero = BigDecimal.ZERO.setScale(mathContext.getPrecision() - exponentStripped);
            } else {
                zero = BigDecimal.ZERO.setScale(mathContext.getPrecision() + exponentStripped + 1);
            }
            return stripped.add(zero, mathContext);
        } catch (ArithmeticException ex) {
            return round(value, mathContext);
        }
    }

    /**
     * Calculates the reciprocal of the specified {@link BigDecimal}.
     *
     * @param x           the {@link BigDecimal}
     * @param mathContext the {@link MathContext} used for the result
     * @return the reciprocal {@link BigDecimal}
     * @throws ArithmeticException if x = 0
     * @throws ArithmeticException if the result is inexact but the
     *                             rounding mode is {@code UNNECESSARY} or
     *                             {@code mc.precision == 0} and the quotient has a
     *                             non-terminating decimal expansion.
     */
    public static BigDecimal reciprocal(BigDecimal x, MathContext mathContext) {
        return BigDecimal.ONE.divide(x, mathContext);
    }

    private static volatile BigDecimal[] factorialCache = null;
    private static final Object FACTORIAL_CACHE_LOCK = new Object();

    /**
     * Calculates the factorial of the specified integer argument.
     *
     * <p>factorial = 1 * 2 * 3 * ... n</p>
     *
     * @param n the {@link BigDecimal}
     * @return the factorial {@link BigDecimal}
     * @throws ArithmeticException if x &lt; 0
     */
    public static BigDecimal factorial(int n) {
        if (n < 0) {
            throw new ArithmeticException("Illegal factorial(n) for n < 0: n = " + n);
        }

        if (factorialCache == null) {
            synchronized (FACTORIAL_CACHE_LOCK) {
                if (factorialCache == null) {
                    factorialCache = new BigDecimal[100];
                    BigDecimal result = ONE;
                    factorialCache[0] = result;
                    for (int i = 1; i < factorialCache.length; i++) {
                        result = result.multiply(valueOf(i));
                        factorialCache[i] = result;
                    }
                }
            }
        }

        if (n < factorialCache.length) {
            return factorialCache[n];
        }

        BigDecimal result = factorialCache[factorialCache.length - 1];
        return result.multiply(BigBasic.factorialRecursion(factorialCache.length, n));
    }

    /**
     * Calculates the factorial of the specified {@link BigDecimal}.
     *
     * <p>This implementation uses
     * <a href="https://en.wikipedia.org/wiki/Spouge%27s_approximation">Spouge's approximation</a>
     * to calculate the factorial for non-integer values.</p>
     *
     * <p>This involves calculating a series of constants that depend on the desired precision.
     * Since this constant calculation is quite expensive (especially for higher precisions),
     * the constants for a specific precision will be cached
     * and subsequent calls to this method with the same precision will be much faster.</p>
     *
     * <p>It is therefore recommended to do one call to this method with the standard precision of your application during the startup phase
     * and to avoid calling it with many different precisions.</p>
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Factorial#Extension_of_factorial_to_non-integer_values_of_argument">Wikipedia: Factorial - Extension of factorial to non-integer values of argument</a></p>
     *
     * @param x           the {@link BigDecimal}
     * @param mathContext the {@link MathContext} used for the result
     * @return the factorial {@link BigDecimal}
     * @throws ArithmeticException           if x is a negative integer value (-1, -2, -3, ...)
     * @throws UnsupportedOperationException if x is a non-integer value and the {@link MathContext} has unlimited precision
     * @see #factorial(int)
     * @see #gamma(BigDecimal, MathContext)
     */
    public static BigDecimal factorial(BigDecimal x, MathContext mathContext) {
        if (isIntValue(x)) {
            return round(factorial(x.intValueExact()), mathContext);
        }

        // https://en.wikipedia.org/wiki/Spouge%27s_approximation
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() << 1, mathContext.getRoundingMode());

        int a = mathContext.getPrecision() * 13 / 10;
        List<BigDecimal> constants = BigBasic.getSpougeFactorialConstants(a);

        BigDecimal bigA = BigDecimal.valueOf(a);

        boolean negative = false;
        BigDecimal factor = constants.get(0);
        for (int k = 1; k < a; k++) {
            BigDecimal bigK = BigDecimal.valueOf(k);
            factor = factor.add(constants.get(k).divide(x.add(bigK), mc));
            negative = !negative;
        }

        BigDecimal result = pow(x.add(bigA), x.add(BigDecimal.valueOf(0.5)), mc);
        result = result.multiply(exp(x.negate().subtract(bigA), mc));
        result = result.multiply(factor);

        return round(result, mathContext);
    }

    /**
     * Calculates the gamma function of the specified {@link BigDecimal}.
     *
     * <p>This implementation uses {@link #factorial(BigDecimal, MathContext)} internally,
     * therefore the performance implications described there apply also for this method.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Gamma_function">Wikipedia: Gamma function</a></p>
     *
     * @param x           the {@link BigDecimal}
     * @param mathContext the {@link MathContext} used for the result
     * @return the gamma {@link BigDecimal}
     * @throws ArithmeticException           if x-1 is a negative integer value (-1, -2, -3, ...)
     * @throws UnsupportedOperationException if x is a non-integer value and the {@link MathContext} has unlimited precision
     * @see #factorial(BigDecimal, MathContext)
     */
    public static BigDecimal gamma(BigDecimal x, MathContext mathContext) {
        return factorial(x.subtract(ONE), mathContext);
    }

    /**
     * Calculates the Bernoulli number for the specified index.
     *
     * <p>This function calculates the <strong>first Bernoulli numbers</strong> and therefore <code>bernoulli(1)</code> returns -0.5</p>
     * <p>Note that <code>bernoulli(x)</code> for all odd x &gt; 1 returns 0</p>
     * <p>See: <a href="https://en.wikipedia.org/wiki/Bernoulli_number">Wikipedia: Bernoulli number</a></p>
     *
     * @param n           the index of the Bernoulli number to be calculated (starting at 0)
     * @param mathContext the {@link MathContext} used for the result
     * @return the Bernoulli number for the specified index
     * @throws ArithmeticException if x &lt; 0
     * @throws ArithmeticException if the result is inexact but the
     *                             rounding mode is {@code UNNECESSARY} or
     *                             {@code mc.precision == 0} and the quotient has a
     *                             non-terminating decimal expansion.
     */
    public static BigDecimal bernoulli(int n, MathContext mathContext) {
        if (n < 0) {
            throw new ArithmeticException("Illegal bernoulli(n) for n < 0: n = " + n);
        }

        BigRational b = BigRational.bernoulli(n);
        return b.toBigDecimal(mathContext);
    }

    /**
     * Calculates {@link BigDecimal} x to the power of {@link BigDecimal} y (x<sup>y</sup>).
     *
     * @param x           the {@link BigDecimal} value to take to the power
     * @param y           the {@link BigDecimal} value to serve as exponent
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated x to the power of y with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     * @see #pow(BigDecimal, long, MathContext)
     */
    public static BigDecimal pow(BigDecimal x, BigDecimal y, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.signum() == 0) {
            switch (y.signum()) {
                case 0 : return round(ONE, mathContext);
                case 1 : return round(ZERO, mathContext);
            }
        }

        // TODO optimize y=0, y=1, y=10^k, y=-1, y=-10^k

        try {
            long longValue = y.longValueExact();
            return pow(x, longValue, mathContext);
        } catch (ArithmeticException ex) {
            // ignored
        }

        if (fractionalPart(y).signum() == 0) {
            return BigBasic.powInteger(x, y, mathContext);
        }

        // x^y = exp(y*log(x))
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = exp(y.multiply(log(x, mc), mc), mc);

        return round(result, mathContext);
    }

    /**
     * Calculates {@link BigDecimal} x to the power of <code>long</code> y (x<sup>y</sup>).
     *
     * <p>The implementation tries to minimize the number of multiplications of {@link BigDecimal x} (using squares whenever possible).</p>
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Exponentiation#Efficient_computation_with_integer_exponents">Wikipedia: Exponentiation - efficient computation</a></p>
     *
     * @param x           the {@link BigDecimal} value to take to the power
     * @param y           the <code>long</code> value to serve as exponent
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated x to the power of y with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException if y is negative and the result is inexact but the
     *                             rounding mode is {@code UNNECESSARY} or
     *                             {@code mc.precision == 0} and the quotient has a
     *                             non-terminating decimal expansion.
     * @throws ArithmeticException if the rounding mode is
     *                             {@code UNNECESSARY} and the
     *                             {@code BigDecimal}  operation would require rounding.
     */
    public static BigDecimal pow(BigDecimal x, long y, MathContext mathContext) {
        MathContext mc = mathContext.getPrecision() == 0 ? mathContext : new MathContext(mathContext.getPrecision() + 10, mathContext.getRoundingMode());

        // TODO optimize y=0, y=1, y=10^k, y=-1, y=-10^k

        if (y < 0) {
            BigDecimal value = reciprocal(pow(x, -y, mc), mc);
            return round(value, mathContext);
        }

        BigDecimal result = ONE;
        while (y > 0) {
            if ((y & 1) == 1) {
                // odd exponent -> multiply result with x
                result = result.multiply(x, mc);
                y -= 1;
            }

            if (y > 0) {
                // even exponent -> square x
                x = x.multiply(x, mc);
            }

            y >>= 1;
        }

        return round(result, mathContext);
    }

    /**
     * Calculates the square root of {@link BigDecimal} x.
     *
     * <p>See <a href="http://en.wikipedia.org/wiki/Square_root">Wikipedia: Square root</a></p>
     *
     * @param x           the {@link BigDecimal} value to calculate the square root
     * @param mathContext the {@link MathContext} used for the calculation and result.
     * @return the calculated square root of x with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x &lt; 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal sqrt(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        switch (x.signum()) {
            case 0:
                return ZERO;
            case -1:
                throw new ArithmeticException("Illegal sqrt(x) for x < 0: x = " + x);
        }

        int maxPrecision = mathContext.getPrecision() + 6;
        BigDecimal acceptableError = ONE.movePointLeft(mathContext.getPrecision() + 1);

        BigDecimal result;
        int adaptivePrecision;
        if (isDoubleValue(x)) {
            result = BigDecimal.valueOf(Math.sqrt(x.doubleValue()));
            adaptivePrecision = EXPECTED_INITIAL_PRECISION;
        } else {
            result = x.multiply(ONE_HALF, mathContext);
            adaptivePrecision = 1;
        }

        BigDecimal last;

        if (adaptivePrecision < maxPrecision) {
            if (result.multiply(result).compareTo(x) == 0) {
                return round(result, mathContext); // early exit if x is a square number
            }

            do {
                last = result;
                adaptivePrecision <<= 1;
                if (adaptivePrecision > maxPrecision) {
                    adaptivePrecision = maxPrecision;
                }
                MathContext mc = new MathContext(adaptivePrecision, mathContext.getRoundingMode());
                result = x.divide(result, mc).add(last).multiply(ONE_HALF, mc);
            }
            while (adaptivePrecision < maxPrecision || result.subtract(last).abs().compareTo(acceptableError) > 0);
        }

        return round(result, mathContext);
    }

    /**
     * Calculates the n'th root of {@link BigDecimal} x.
     *
     * <p>See <a href="https://en.wikipedia.org/wiki/Nth_root">Wikipedia: Nth root</a></p>
     *
     * @param x           the {@link BigDecimal} value to calculate the n'th root
     * @param n           the {@link BigDecimal} defining the root
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated n'th root of x with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if n &lt;= 0
     * @throws ArithmeticException           if x &lt; 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal root(BigDecimal x, BigDecimal n, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);

        switch (n.signum()) {
            case -1:
            case 0:
                throw new ArithmeticException("Illegal root(x, n) for n <= 0: n = " + n);
        }

        switch (x.signum()) {
            case 0:
                return ZERO;
            case -1:
                throw new ArithmeticException("Illegal root(x, n) for x < 0: x = " + x);
        }

        if (isDoubleValue(x) && isDoubleValue(n)) {
            double initialResult = Math.pow(x.doubleValue(), 1.0 / n.doubleValue());
            if (Double.isFinite(initialResult)) {
                long start = System.nanoTime();
                BigDecimal bigDecimal = BigBasic.rootUsingNewtonRaphson(x, n, valueOf(initialResult), mathContext);
                System.out.println("taken: " + (System.nanoTime() - start));
                return bigDecimal;
            }
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        return pow(x, BigDecimal.ONE.divide(n, mc), mathContext);
    }

    /**
     * Calculates the natural logarithm of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Natural_logarithm">Wikipedia: Natural logarithm</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the natural logarithm for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated natural logarithm {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x &lt;= 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal log(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.signum() <= 0) {
            throw new ArithmeticException("Illegal log(x) for x <= 0: x = " + x);
        }
        if (x.compareTo(ONE) == 0) {
            return ZERO;
        }

        BigDecimal result;
        switch (x.compareTo(TEN)) {
            case 0:
                result = BigBasic.logTen(mathContext);
                break;
            case 1:
                result = BigBasic.logUsingExponent(x, mathContext);
                break;
            default:
                result = BigBasic.logUsingTwoThree(x, mathContext);
        }

        return round(result, mathContext);
    }

    /**
     * Calculates the logarithm of {@link BigDecimal} x to the base 2.
     *
     * @param x           the {@link BigDecimal} to calculate the logarithm base 2 for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated natural logarithm {@link BigDecimal} to the base 2 with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x &lt;= 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal log2(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());

        BigDecimal result = log(x, mc).divide(BigBasic.logTwo(mc), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the logarithm of {@link BigDecimal} x to the base 10.
     *
     * @param x           the {@link BigDecimal} to calculate the logarithm base 10 for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated natural logarithm {@link BigDecimal} to the base 10 with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x &lt;= 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal log10(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 2, mathContext.getRoundingMode());

        BigDecimal result = log(x, mc).divide(BigBasic.logTen(mc), mc);
        BigDecimal rounded = round(result, mathContext);
        return rounded.stripTrailingZeros();
    }

    private static volatile BigDecimal piCache;
    private static final Object PI_CACHE_LOCK = new Object();

    /**
     * Returns the number pi.
     *
     * <p>See <a href="https://en.wikipedia.org/wiki/Pi">Wikipedia: Pi</a></p>
     *
     * @param mathContext the {@link MathContext} used for the result
     * @return the number pi with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal pi(MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        BigDecimal result;

        synchronized (PI_CACHE_LOCK) {
            if (piCache != null && mathContext.getPrecision() <= piCache.precision()) {
                result = piCache;
            } else {
                piCache = BigBasic.piChudnovski(mathContext);
                return piCache;
            }
        }

        return round(result, mathContext);
    }

    private static volatile BigDecimal eCache;
    private static final Object E_CACHE_LOCK = new Object();

    /**
     * Returns the number e.
     *
     * <p>See <a href="https://en.wikipedia.org/wiki/E_(mathematical_constant)">Wikipedia: E (mathematical_constant)</a></p>
     *
     * @param mathContext the {@link MathContext} used for the result
     * @return the number e with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal e(MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        BigDecimal result;

        synchronized (E_CACHE_LOCK) {
            if (eCache != null && mathContext.getPrecision() <= eCache.precision()) {
                result = eCache;
            } else {
                eCache = exp(ONE, mathContext);
                return eCache;
            }
        }

        return round(result, mathContext);
    }

    /**
     * Calculates the natural exponent of {@link BigDecimal} x (e<sup>x</sup>).
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Exponent">Wikipedia: Exponent</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the exponent for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated exponent {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal exp(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.signum() == 0) {
            return ONE;
        }

        return BigBasic.expIntegralFractional(x, mathContext);
    }

    /**
     * Calculates the sine (sinus) of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Sine">Wikipedia: Sine</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the sine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated sine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal sin(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        if (x.abs().compareTo(ROUGHLY_TWO_PI) > 0) {
            MathContext mc2 = new MathContext(mc.getPrecision() + 4, mathContext.getRoundingMode());
            BigDecimal twoPi = TWO.multiply(pi(mc2));
            x = x.remainder(twoPi, mc2);
        }

        BigDecimal result = SinCalculator.INSTANCE.calculate(x, mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc sine (inverted sine) of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Arcsine">Wikipedia: Arcsine</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc sine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc sine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x &gt; 1 or x &lt; -1
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal asin(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.compareTo(ONE) > 0) {
            throw new ArithmeticException("Illegal asin(x) for x > 1: x = " + x);
        }
        if (x.compareTo(MINUS_ONE) < 0) {
            throw new ArithmeticException("Illegal asin(x) for x < -1: x = " + x);
        }

        if (x.signum() == -1) {
            return asin(x.negate(), mathContext).negate();
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        if (x.compareTo(BigDecimal.valueOf(0.707107)) >= 0) {
            BigDecimal xTransformed = sqrt(ONE.subtract(x.multiply(x)), mc);
            return acos(xTransformed, mathContext);
        }

        BigDecimal result = AsinCalculator.INSTANCE.calculate(x, mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the cosine (cosinus) of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Cosine">Wikipedia: Cosine</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the cosine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated cosine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal cos(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        if (x.abs().compareTo(ROUGHLY_TWO_PI) > 0) {
            MathContext mc2 = new MathContext(mc.getPrecision() + 4, mathContext.getRoundingMode());
            BigDecimal twoPi = TWO.multiply(pi(mc2), mc2);
            x = x.remainder(twoPi, mc2);
        }

        BigDecimal result = CosCalculator.INSTANCE.calculate(x, mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc cosine (inverted cosine) of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Arccosine">Wikipedia: Arccosine</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc cosine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc sine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x &gt; 1 or x &lt; -1
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal acos(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.compareTo(ONE) > 0) {
            throw new ArithmeticException("Illegal acos(x) for x > 1: x = " + x);
        }
        if (x.compareTo(MINUS_ONE) < 0) {
            throw new ArithmeticException("Illegal acos(x) for x < -1: x = " + x);
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        BigDecimal result = pi(mc).divide(TWO, mc).subtract(asin(x, mc));
        return round(result, mathContext);
    }

    /**
     * Calculates the tangens of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Tangens">Wikipedia: Tangens</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the tangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated tangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal tan(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.signum() == 0) {
            return ZERO;
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        BigDecimal result = sin(x, mc).divide(cos(x, mc), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc tangens (inverted tangens) of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Arctangens">Wikipedia: Arctangens</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc tangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc tangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal atan(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());

        x = x.divide(sqrt(ONE.add(x.multiply(x, mc)), mc), mc);

        BigDecimal result = asin(x, mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc tangens (inverted tangens) of {@link BigDecimal} y / x in the range -<i>pi</i> to <i>pi</i>.
     *
     * <p>This is useful to calculate the angle <i>theta</i> from the conversion of rectangular
     * coordinates (<code>x</code>,&nbsp;<code>y</code>) to polar coordinates (r,&nbsp;<i>theta</i>).</p>
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Atan2">Wikipedia: Atan2</a></p>
     *
     * @param y           the {@link BigDecimal}
     * @param x           the {@link BigDecimal}
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc tangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x = 0 and y = 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal atan2(BigDecimal y, BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 3, mathContext.getRoundingMode());

        if (x.signum() > 0) { // x > 0
            return atan(y.divide(x, mc), mathContext);
        } else if (x.signum() < 0) {
            if (y.signum() > 0) {  // x < 0 && y > 0
                return atan(y.divide(x, mc), mc).add(pi(mc), mathContext);
            } else if (y.signum() < 0) { // x < 0 && y < 0
                return atan(y.divide(x, mc), mc).subtract(pi(mc), mathContext);
            } else { // x < 0 && y = 0
                return pi(mathContext);
            }
        } else {
            if (y.signum() > 0) { // x == 0 && y > 0
                return pi(mc).divide(TWO, mathContext);
            } else if (y.signum() < 0) {  // x == 0 && y < 0
                return pi(mc).divide(TWO, mathContext).negate();
            } else {
                throw new ArithmeticException("Illegal atan2(y, x) for x = 0; y = 0");
            }
        }
    }

    /**
     * Calculates the cotangens of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Cotangens">Wikipedia: Cotangens</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the cotangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated cotanges {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws ArithmeticException           if x = 0
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal cot(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        if (x.signum() == 0) {
            throw new ArithmeticException("Illegal cot(x) for x = 0");
        }

        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        BigDecimal result = cos(x, mc).divide(sin(x, mc), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the inverse cotangens (arc cotangens) of {@link BigDecimal} x.
     *
     * <p>See: <a href="http://en.wikipedia.org/wiki/Arccotangens">Wikipedia: Arccotangens</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc cotangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc cotangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal acot(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        BigDecimal result = pi(mc).divide(TWO, mc).subtract(atan(x, mc));
        return round(result, mathContext);
    }

    /**
     * Calculates the hyperbolic sine of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the hyperbolic sine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated hyperbolic sine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal sinh(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        BigDecimal result = SinhCalculator.INSTANCE.calculate(x, mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the hyperbolic cosine of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the hyperbolic cosine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated hyperbolic cosine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal cosh(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 4, mathContext.getRoundingMode());
        BigDecimal result = CoshCalculator.INSTANCE.calculate(x, mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the hyperbolic tangens of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the hyperbolic tangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated hyperbolic tangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal tanh(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = sinh(x, mc).divide(cosh(x, mc), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the hyperbolic cotangens of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the hyperbolic cotangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated hyperbolic cotangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal coth(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = cosh(x, mc).divide(sinh(x, mc), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc hyperbolic sine (inverse hyperbolic sine) of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc hyperbolic sine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc hyperbolic sine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal asinh(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 10, mathContext.getRoundingMode());
        BigDecimal result = log(x.add(sqrt(x.multiply(x, mc).add(ONE, mc), mc)), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc hyperbolic cosine (inverse hyperbolic cosine) of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc hyperbolic cosine for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc hyperbolic cosine {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal acosh(BigDecimal x, MathContext mathContext) {
        if (x.compareTo(BigDecimal.ONE) < 0) {
            throw new ArithmeticException("Illegal acosh(x) for x >= 1: x = " + x);
        }
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = log(x.add(sqrt(x.multiply(x).subtract(ONE), mc)), mc);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc hyperbolic tangens (inverse hyperbolic tangens) of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc hyperbolic tangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc hyperbolic tangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal atanh(BigDecimal x, MathContext mathContext) {
        if (x.compareTo(BigDecimal.ONE) >= 0) {
            throw new ArithmeticException("Illegal atanh(x) for x >= 1: x = " + x);
        }
        if (x.compareTo(MINUS_ONE) <= 0) {
            throw new ArithmeticException("Illegal atanh(x) for x <= -1: x = " + x);
        }

        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = log(ONE.add(x).divide(ONE.subtract(x), mc), mc).multiply(ONE_HALF);
        return round(result, mathContext);
    }

    /**
     * Calculates the arc hyperbolic cotangens (inverse hyperbolic cotangens) of {@link BigDecimal} x.
     *
     * <p>See: <a href="https://en.wikipedia.org/wiki/Hyperbolic_function">Wikipedia: Hyperbolic function</a></p>
     *
     * @param x           the {@link BigDecimal} to calculate the arc hyperbolic cotangens for
     * @param mathContext the {@link MathContext} used for the result
     * @return the calculated arc hyperbolic cotangens {@link BigDecimal} with the precision specified in the <code>mathContext</code>
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal acoth(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = log(x.add(ONE).divide(x.subtract(ONE), mc), mc).multiply(ONE_HALF);
        return round(result, mathContext);
    }

    /**
     * Converts an angle measured in radians to an approximately equivalent angle measured in degrees.
     * The conversion from radians to degrees is generally inexact, it uses the number PI with the precision specified in the mathContext.
     *
     * @param x           an angle in radians.
     * @param mathContext the {@link MathContext} used for the result
     * @return The angle in degrees.
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal toDegrees(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = x.multiply(ONE_HUNDRED_EIGHTY.divide(pi(mc), mc),  mc);
        return round(result, mathContext);
    }

    /**
     * Converts an angle measured in degrees to an approximately equivalent angle measured in radians.
     * The conversion from degrees to radians is generally inexact, it uses the number PI with the precision specified in the mathContext.
     *
     * @param x           an angle in degrees.
     * @param mathContext the {@link MathContext} used for the result
     * @return The angle in radians.
     * @throws UnsupportedOperationException if the {@link MathContext} has unlimited precision
     */
    public static BigDecimal toRadians(BigDecimal x, MathContext mathContext) {
        BigBasic.checkMathContext(mathContext);
        MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
        BigDecimal result = x.multiply(pi(mc).divide(ONE_HUNDRED_EIGHTY, mc), mc);
        return round(result, mathContext);
    }

    /**
     * Fraction calculation using Continued fraction algorithm.
     * <p>See: <a href="https://jonisalonen.com/2012/converting-decimal-numbers-to-ratios/">Converting decimal numbers to fractions</a></p>
     *
     * <p>Examples:</p>
     * <ul>
     * <li><code>convertToFraction(new BigDecimal("0.894784"), new BigDecimal("9999999999999999999999"), new BigDecimal("9999999999999999999999"), 12, MathContext.DECIMAL128);</code> returns 13981/15625</li>
     * </ul>
     *
     * @param value               decimal
     * @param maxNumeratorValue   maximum numerator value to calculate
     * @param maxDenominatorValue maximum denominator value to calculate
     * @param tolerance           uses in calculation
     * @param mathContext         the {@link MathContext} used for the calculation and result.
     * @return {@link BigIntegerFraction}
     * @throws IllegalArgumentException
     * @throws ArithmeticException
     */
    public static BigIntegerFraction convertToFraction(BigDecimal value,
                                                       BigDecimal maxNumeratorValue,
                                                       BigDecimal maxDenominatorValue,
                                                       int tolerance,
                                                       MathContext mathContext) throws IllegalArgumentException, ArithmeticException {
        int mcPrecision = mathContext.getPrecision();
        if (tolerance >= mcPrecision) {
            throw new IllegalArgumentException("Tolerance must be less than MathContext precision");
        }
        BigDecimal toleranceBigDecimal = new BigDecimal("1E-" + (mcPrecision - tolerance));

        boolean positive = value.compareTo(BigDecimal.ZERO) >= 0;
        value = value.abs(mathContext);

        BigDecimal remainder = value.remainder(BigDecimal.ONE, mathContext);
        if (remainder.compareTo(BigDecimal.ZERO) == 0) {
            if (value.compareTo(maxNumeratorValue) > 0) {
                throw new ArithmeticException("Unable to find numerator for " + value + " value because it reached the max numerator limit " + maxNumeratorValue);
            }
            BigInteger numerator = value.toBigInteger();
            return new BigIntegerFraction(positive, numerator, BigInteger.ONE);
        }

        BigDecimal pn1 = BigDecimal.ONE;
        BigDecimal pn2 = BigDecimal.ZERO;
        BigDecimal qn1 = BigDecimal.ZERO;
        BigDecimal qn2 = BigDecimal.ONE;
        BigDecimal x = value;

        BigDecimal left;
        BigDecimal right;

        do {
            BigDecimal a = x.setScale(0, RoundingMode.FLOOR);

            BigDecimal aux = pn1;
            pn1 = a.multiply(pn1, mathContext).add(pn2, mathContext);
            if (pn1.compareTo(maxNumeratorValue) > 0) {
                throw new ArithmeticException("Unable to find numerator for " + value + " value because it reached the max numerator limit " + maxNumeratorValue);
            }

            pn2 = aux;
            aux = qn1;
            qn1 = a.multiply(qn1, mathContext).add(qn2, mathContext);
            if (qn1.compareTo(maxDenominatorValue) > 0) {
                throw new ArithmeticException("Unable to find denominator for " + value + " value because it reached the max denominator limit " + maxDenominatorValue);
            }

            BigDecimal numeratorDivideDenominator = pn1.divide(qn1, mathContext);
            left = value.subtract(numeratorDivideDenominator, mathContext).abs(mathContext);
            right = value.multiply(toleranceBigDecimal, mathContext);

            qn2 = aux;
            BigDecimal xSubtractA = x.subtract(a, mathContext);
            if (xSubtractA.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            x = BigDecimal.ONE.divide(xSubtractA, mathContext);
        } while (left.compareTo(right) > 0);

        BigInteger numerator = pn1.toBigInteger();
        if (new BigDecimal(numerator).compareTo(pn1) != 0) {
            throw new ArithmeticException("Numerator is not an Integer: " + pn1);
        }
        BigInteger denominator = qn1.toBigInteger();
        if (new BigDecimal(denominator).compareTo(qn1) != 0) {
            throw new ArithmeticException("Denominator is not an Integer: " + qn1);
        }

        return new BigIntegerFraction(positive, numerator, denominator);
    }

    //TODO replace with fast add
    //TODO change type to BigInteger
    /**
     * Function check that given value is prime number
     *
     * @param value       BigInteger
     * @param mathContext the {@link MathContext} used for the calculation and result.
     * @return true if value is prime number, otherwise false
     */
    public static boolean checkPrimeNumber(BigDecimal value, MathContext mathContext) {
        if (value.compareTo(BigDecimal.ONE) <= 0)
            return false;
        else if (value.compareTo(BigDecimal.valueOf(3)) <= 0)
            return true;
        else if (value.remainder(BigDecimal.valueOf(2), mathContext).compareTo(BigDecimal.ZERO) == 0 ||
                value.remainder(BigDecimal.valueOf(3), mathContext).compareTo(BigDecimal.ZERO) == 0)
            return false;
        BigDecimal n = BigDecimal.valueOf(5);
        while (n.multiply(n, mathContext).compareTo(value) <= 0) {
            if (value.remainder(n).compareTo(BigDecimal.ZERO) == 0 || value.remainder(n.add(BigDecimal.valueOf(2), mathContext)).compareTo(BigDecimal.ZERO) == 0)
                return false;
            n = n.add(BigDecimal.valueOf(6), mathContext);
        }
        return true;
    }

    private BigDecimalMath() {
        // prevent instances
    }
}