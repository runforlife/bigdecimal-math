package io.github.runforlife.bigdecimal.math;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A rational number represented as a quotient of two values.
 * 
 * <p>Basic calculations with rational numbers (+ - * /) have no loss of precision.
 * This allows to use {@link BigRational} as a replacement for {@link BigDecimal} if absolute accuracy is desired.</p>
 * 
 * <p><a href="http://en.wikipedia.org/wiki/Rational_number">Wikipedia: Rational number</a></p>
 * 
 * <p>The values are internally stored as {@link BigDecimal} (for performance optimizations) but represented
 * as {@link BigInteger} (for mathematical correctness)
 * when accessed with {@link #getNumeratorBigInteger()} and {@link #getDenominatorBigInteger()}.</p>
 * 
 * <p>The following basic calculations have no loss of precision:</p>
 * <ul>
 * <li>{@link #add(BigRational)}</li>
 * <li>{@link #subtract(BigRational)}</li>
 * <li>{@link #multiply(BigRational)}</li>
 * <li>{@link #divide(BigRational)}</li>
 * <li>{@link #pow(int)}</li>
 * </ul>
 * 
 * <p>The following calculations are special cases of the ones listed above and have no loss of precision:</p>
 * <ul>
 * <li>{@link #negate()}</li>
 * <li>{@link #reciprocal()}</li>
 * <li>{@link #increment()}</li>
 * <li>{@link #decrement()}</li>
 * </ul>
 * 
 * <p>Any {@link BigRational} value can be converted into an arbitrary {@link #withPrecision(int) precision} (number of significant digits)
 * or {@link #withScale(int) scale} (number of digits after the decimal point).</p>
 */
public class BigRational extends Number implements Comparable<BigRational>, Serializable {

	/**
	 * The value 0 as {@link BigRational}.
	 */
	public static final BigRational ZERO = new BigRational(0);
	/**
	 * The value 1 as {@link BigRational}.
	 */
	public static final BigRational ONE = new BigRational(1);
	/**
	 * The value 2 as {@link BigRational}.
	 */
	public static final BigRational TWO = new BigRational(2);
	/**
	 * The value 10 as {@link BigRational}.
	 */
	public static final BigRational TEN = new BigRational(10);

	private final BigDecimal numerator;

	private final BigDecimal denominator;

	private BigRational(int value) {
		this(BigDecimal.valueOf(value), BigDecimal.ONE);
	}

	private BigRational(BigDecimal num, BigDecimal denom) {
		BigDecimal n = num;
		BigDecimal d = denom;

		if (d.signum() == 0) {
			throw new ArithmeticException("Divide by zero");
		}

		if (d.signum() < 0) {
			n = n.negate();
			d = d.negate();
		}

		numerator = n;
		denominator = d;
	}

	/**
	 * Returns the numerator of this rational number as BigInteger.
	 * 
	 * @return the numerator as BigInteger
	 */
	public BigInteger getNumeratorBigInteger() {
		return numerator.toBigInteger();
	}

	/**
	 * Returns the numerator of this rational number as BigDecimal.
	 * 
	 * @return the numerator as BigDecimal
	 */
	public BigDecimal getNumerator() {
		return numerator;
	}

	/**
	 * Returns the denominator of this rational number as BigInteger.
	 * 
	 * <p>Guaranteed to not be 0.</p>
	 * <p>Guaranteed to be positive.</p>
	 * 
	 * @return the denominator as BigInteger
	 */
	public BigInteger getDenominatorBigInteger() {
		return denominator.toBigInteger();
	}

	/**
	 * Returns the denominator of this rational number as BigDecimal.
	 * 
	 * <p>Guaranteed to not be 0.</p>
	 * <p>Guaranteed to be positive.</p>
	 * 
	 * @return the denominator as BigDecimal
	 */
	public BigDecimal getDenominator() {
		return denominator;
	}

	/**
	 * Reduces this rational number to the smallest numerator/denominator with the same value.
	 * 
	 * @return the reduced rational number
	 */
	public BigRational reduce() {
		BigInteger n = numerator.toBigInteger();
		BigInteger d = denominator.toBigInteger();

		BigInteger gcd = n.gcd(d);
		n = n.divide(gcd);
		d = d.divide(gcd);

		return valueOf(n, d);
	}

	/**
	 * Returns the integer part of this rational number.
	 * 
	 * <p>Examples:</p>
	 * <ul>
	 * <li><code>BigRational.valueOf(3.5).integerPart()</code> returns <code>BigRational.valueOf(3)</code></li>
	 * </ul>
	 * 
	 * @return the integer part of this rational number
	 */
	public BigRational integerPart() {
		return of(numerator.subtract(numerator.remainder(denominator)), denominator);
	}

	/**
	 * Returns the fraction part of this rational number.
	 * 
	 * <p>Examples:</p>
	 * <ul>
	 * <li><code>BigRational.valueOf(3.5).integerPart()</code> returns <code>BigRational.valueOf(0.5)</code></li>
	 * </ul>
	 * 
	 * @return the fraction part of this rational number
	 */
	public BigRational fractionPart() {
		return of(numerator.remainder(denominator), denominator);
	}
	
	/**
	 * Negates this rational number (inverting the sign).
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * <p>Examples:</p>
	 * <ul>
	 * <li><code>BigRational.valueOf(3.5).negate()</code> returns <code>BigRational.valueOf(-3.5)</code></li>
	 * </ul>
	 * 
	 * @return the negated rational number
	 */
	public BigRational negate() {
		if (isZero()) {
			return this;
		}

		return of(numerator.negate(), denominator);
	}

	/**
	 * Calculates the reciprocal of this rational number (1/x).
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * <p>Examples:</p>
	 * <ul>
	 * <li><code>BigRational.valueOf(0.5).reciprocal()</code> returns <code>BigRational.valueOf(2)</code></li>
	 * <li><code>BigRational.valueOf(-2).reciprocal()</code> returns <code>BigRational.valueOf(-0.5)</code></li>
	 * </ul>
	 * 
	 * @return the reciprocal rational number
	 * @throws ArithmeticException if this number is 0 (division by zero)
	 */
	public BigRational reciprocal() {
		return of(denominator, numerator);
	}

	/**
	 * Returns the absolute value of this rational number.
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * <p>Examples:</p>
	 * <ul>
	 * <li><code>BigRational.valueOf(-2).abs()</code> returns <code>BigRational.valueOf(2)</code></li>
	 * <li><code>BigRational.valueOf(2).abs()</code> returns <code>BigRational.valueOf(2)</code></li>
	 * </ul>
	 * 
	 * @return the absolute rational number (positive, or 0 if this rational is 0)
	 */
	public BigRational abs() {
		return isPositive() ? this : negate();
	}

	/**
	 * Returns the signum function of this rational number.
	 *
	 * @return -1, 0 or 1 as the value of this rational number is negative, zero or positive.
	 */
	public int signum() {
		return numerator.signum();
	}

	/**
	 * Calculates the increment of this rational number (+ 1).
	 * 
	 * <p>This is functionally identical to
	 * <code>this.add(BigRational.ONE)</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @return the incremented rational number
	 */
	public BigRational increment() {
		return of(numerator.add(denominator), denominator);
	}

	/**
	 * Calculates the decrement of this rational number (- 1).
	 * 
	 * <p>This is functionally identical to
	 * <code>this.subtract(BigRational.ONE)</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @return the decremented rational number
	 */
	public BigRational decrement() {
		return of(numerator.subtract(denominator), denominator);
	}

	/**
	 * Calculates the addition (+) of this rational number and the specified argument.
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the rational number to add
	 * @return the resulting rational number
	 */
	public BigRational add(BigRational value) {
		if (denominator.compareTo(value.denominator) == 0) {
			return of(numerator.add(value.numerator), denominator);
		}

		BigDecimal n = numerator.multiply(value.denominator).add(value.numerator.multiply(denominator));
		BigDecimal d = denominator.multiply(value.denominator);
		return of(n, d);
	}

	private BigRational add(BigDecimal value) {
		return of(numerator.add(value.multiply(denominator)), denominator);
	}
	
	/**
	 * Calculates the addition (+) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.add(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the {@link BigInteger} to add
	 * @return the resulting rational number
	 */
	public BigRational add(BigInteger value) {
		if (value.equals(BigInteger.ZERO)) {
			return this;
		}
		return add(new BigDecimal(value));
	}

	/**
	 * Calculates the addition (+) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.add(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the int value to add
	 * @return the resulting rational number
	 */
	public BigRational add(int value) {
		if (value == 0) {
			return this;
		}
		return add(BigInteger.valueOf(value));
	}

	/**
	 * Calculates the subtraction (-) of this rational number and the specified argument.
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the rational number to subtract
	 * @return the resulting rational number
	 */
	public BigRational subtract(BigRational value) {
		if (denominator.compareTo(value.denominator) == 0) {
			return of(numerator.subtract(value.numerator), denominator);
		}

		BigDecimal n = numerator.multiply(value.denominator).subtract(value.numerator.multiply(denominator));
		BigDecimal d = denominator.multiply(value.denominator);
		return of(n, d);
	}

	private BigRational subtract(BigDecimal value) {
		return of(numerator.subtract(value.multiply(denominator)), denominator);
	}
	
	/**
	 * Calculates the subtraction (-) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.subtract(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the {@link BigInteger} to subtract
	 * @return the resulting rational number
	 */
	public BigRational subtract(BigInteger value) {
		if (value.equals(BigInteger.ZERO)) {
			return this;
		}
		return subtract(new BigDecimal(value));
	}

	/**
	 * Calculates the subtraction (-) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.subtract(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the int value to subtract
	 * @return the resulting rational number
	 */
	public BigRational subtract(int value) {
		if (value == 0) {
			return this;
		}
		return subtract(BigInteger.valueOf(value));
	}

	/**
	 * Calculates the multiplication (*) of this rational number and the specified argument.
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the rational number to multiply
	 * @return the resulting rational number
	 */
	public BigRational multiply(BigRational value) {
		if (isZero() || value.isZero()) {
			return ZERO;
		}
		if (equals(ONE)) {
			return value;
		}
		if (value.equals(ONE)) {
			return this;
		}

		BigDecimal n = numerator.multiply(value.numerator);
		BigDecimal d = denominator.multiply(value.denominator);
		return of(n, d);
	}

	// private, because we want to hide that we use BigDecimal internally
	private BigRational multiply(BigDecimal value) {
		BigDecimal n = numerator.multiply(value);
		BigDecimal d = denominator;
		return of(n, d);
	}
	
	/**
	 * Calculates the multiplication (*) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.multiply(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the {@link BigInteger} to multiply
	 * @return the resulting rational number
	 */
	public BigRational multiply(BigInteger value) {
		if (isZero() || value.signum() == 0) {
			return ZERO;
		}
		if (equals(ONE)) {
			return valueOf(value);
		}
		if (value.equals(BigInteger.ONE)) {
			return this;
		}

		return multiply(new BigDecimal(value));
	}

	/**
	 * Calculates the multiplication (*) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.multiply(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the int value to multiply
	 * @return the resulting rational number
	 */
	public BigRational multiply(int value) {
		return multiply(BigInteger.valueOf(value));
	}

	/**
	 * Calculates the division (/) of this rational number and the specified argument.
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the rational number to divide (0 is not allowed)
	 * @return the resulting rational number
	 * @throws ArithmeticException if the argument is 0 (division by zero)
	 */
	public BigRational divide(BigRational value) {
		if (value.equals(ONE)) {
			return this;
		}

		BigDecimal n = numerator.multiply(value.denominator);
		BigDecimal d = denominator.multiply(value.numerator);
		return of(n, d);
	}

	private BigRational divide(BigDecimal value) {
		BigDecimal n = numerator;
		BigDecimal d = denominator.multiply(value);
		return of(n, d);
	}
	
	/**
	 * Calculates the division (/) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.divide(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the {@link BigInteger} to divide (0 is not allowed)
	 * @return the resulting rational number
	 * @throws ArithmeticException if the argument is 0 (division by zero)
	 */
	public BigRational divide(BigInteger value) {
		if (value.equals(BigInteger.ONE)) {
			return this;
		}

		return divide(new BigDecimal(value));
	}

	/**
	 * Calculates the division (/) of this rational number and the specified argument.
	 * 
	 * <p>This is functionally identical to
	 * <code>this.divide(BigRational.valueOf(value))</code>
	 * but slightly faster.</p>
	 * 
	 * <p>The result has no loss of precision.</p>
	 * 
	 * @param value the int value to divide (0 is not allowed)
	 * @return the resulting rational number
	 * @throws ArithmeticException if the argument is 0 (division by zero)
	 */
	public BigRational divide(int value) {
		return divide(BigInteger.valueOf(value));
	}

	/**
	 * Returns whether this rational number is zero.
	 * 
	 * @return <code>true</code> if this rational number is zero (0), <code>false</code> if it is not zero
	 */
	public boolean isZero() {
		return numerator.signum() == 0;
	}

	private boolean isPositive() {
		return numerator.signum() > 0;
	}

	/**
	 * Returns whether this rational number is an integer number without fraction part.
	 * 
	 * @return <code>true</code> if this rational number is an integer number, <code>false</code> if it has a fraction part
	 */
	public boolean isInteger() {
		return isIntegerInternal() || reduce().isIntegerInternal();
	}

	/**
	 * Returns whether this rational number is an integer number without fraction part.
	 * 
	 * <p>Will return <code>false</code> if this number is not reduced to the integer representation yet (e.g. 4/4 or 4/2)</p>
	 * 
	 * @return <code>true</code> if this rational number is an integer number, <code>false</code> if it has a fraction part
	 * @see #isInteger()
	 */
	private boolean isIntegerInternal() {
		return denominator.compareTo(BigDecimal.ONE) == 0;
	}

	/**
	 * Calculates this rational number to the power (x<sup>y</sup>) of the specified argument.
	 * 
	 * <p>The result has no loss of precision.</p>
	 *
	 * @param exponent exponent to which this rational number is to be raised
	 * @return the resulting rational number
	 */
	public BigRational pow(int exponent) {
		if (exponent == 0) {
			return ONE;
		}
		if (exponent == 1) {
			return this;
		}

		final BigInteger n;
		final BigInteger d;
		if (exponent > 0) {
			n = numerator.toBigInteger().pow(exponent);
			d = denominator.toBigInteger().pow(exponent);
		}
		else {
			n = denominator.toBigInteger().pow(-exponent);
			d = numerator.toBigInteger().pow(-exponent);
		}
		return valueOf(n, d);
	}

	/**
	 * Finds the minimum (smaller) of two rational numbers.
	 * 
	 * @param value the rational number to compare with
	 * @return the minimum rational number, either <code>this</code> or the argument <code>value</code>
	 */
	private BigRational min(BigRational value) {
		return compareTo(value) <= 0 ? this : value;
	}

	/**
	 * Finds the maximum (larger) of two rational numbers.
	 * 
	 * @param value the rational number to compare with
	 * @return the minimum rational number, either <code>this</code> or the argument <code>value</code>
	 */
	private BigRational max(BigRational value) {
		return compareTo(value) >= 0 ? this : value;
	}

	/**
	 * Returns a rational number with approximatively <code>this</code> value and the specified precision.
	 * 
	 * @param precision the precision (number of significant digits) of the calculated result, or 0 for unlimited precision
	 * @return the calculated rational number with the specified precision
	 */
	public BigRational withPrecision(int precision) {
		return valueOf(toBigDecimal(new MathContext(precision)));
	}

	/**
	 * Returns a rational number with approximatively <code>this</code> value and the specified scale.
	 * 
	 * @param scale the scale (number of digits after the decimal point) of the calculated result
	 * @return the calculated rational number with the specified scale
	 */
	public BigRational withScale(int scale) {
		return valueOf(toBigDecimal().setScale(scale, RoundingMode.HALF_UP));
	}

	private static int countDigits(BigInteger number) {
		double factor = Math.log(2) / Math.log(10);
		int digitCount = (int) (factor * number.bitLength() + 1);
		if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
			return digitCount - 1;
		}
		return digitCount;
	}

	// TODO what is precision of a rational?
	private int precision() {
		return countDigits(numerator.toBigInteger()) + countDigits(denominator.toBigInteger());
	}

	/**
	 * Returns this rational number as a double value.
	 *
	 * <p>If the rational number cannot be represented as double then one of the following results will be returned:</p>
	 * <ul>
	 *   <li>&gt; <code>Double.MAX_VALUE</code> returns {@link Double#POSITIVE_INFINITY}</li>
	 *   <li>&lt; <code>-Double.MAX_VALUE</code> returns {@link Double#NEGATIVE_INFINITY}</li>
	 *   <li>&lt; <code>Double.MIN_VALUE</code> returns <code>+0.0</code></li>
	 *   <li>&gt; <code>-Double.MIN_VALUE</code> returns <code>-0.0</code></li>
	 * </ul>
	 *
	 * @return the double value
	 */
	public double toDouble() {
		return toBigDecimal().doubleValue();
	}

	/**
	 * Returns this rational number as a float value.
	 *
	 * <p>If the rational number cannot be represented as float then one of the following results will be returned:</p>
	 * <ul>
	 *   <li>&gt; <code>Float.MAX_VALUE</code> returns {@link Float#POSITIVE_INFINITY}</li>
	 *   <li>&lt; <code>-Float.MAX_VALUE</code> returns {@link Float#NEGATIVE_INFINITY}</li>
	 *   <li>&lt; <code>Float.MIN_VALUE</code> returns <code>+0.0f</code></li>
	 *   <li>&gt; <code>-Float.MIN_VALUE</code> returns <code>-0.0f</code></li>
	 * </ul>
	 *
	 * @return the float value
	 */
	public float toFloat() {
		return toBigDecimal().floatValue();
	}

	/**
	 * Returns this rational number as a {@link BigDecimal}.
	 * 
	 * @return the {@link BigDecimal} value
	 */
	public BigDecimal toBigDecimal() {
		int precision = Math.max(precision(), MathContext.DECIMAL128.getPrecision());
		return toBigDecimal(new MathContext(precision));
	}

	/**
	 * Returns this rational number as a {@link BigDecimal} with the precision specified by the {@link MathContext}.
	 * 
	 * @param mc the {@link MathContext} specifying the precision of the calculated result
	 * @return the {@link BigDecimal}
	 */
	public BigDecimal toBigDecimal(MathContext mc) {
		return numerator.divide(denominator, mc);
	}

	@Override
	public int compareTo(BigRational other) {
		if (this == other) {
			return 0;
		}
		return numerator.multiply(other.denominator).compareTo(denominator.multiply(other.numerator));
	}

	@Override
	public int hashCode() {
		if (isZero()) {
			return 0;
		}
		return numerator.hashCode() + denominator.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof BigRational)) {
			return false;
		}

		BigRational other = (BigRational) obj;
		if (numerator.compareTo(other.numerator) != 0) {
			return false;
		}
		return denominator.compareTo(other.denominator) == 0;
	}

	@Override
	public String toString() {
		if (isZero()) {
			return "0";
		}
		if (isIntegerInternal()) {
			return numerator.toString();
		}
		return toBigDecimal().toString();
	}

	/**
	 * Returns a plain string representation of this rational number without any exponent.
	 * 
	 * @return the plain string representation
	 * @see BigDecimal#toPlainString()
	 */
	public String toPlainString() {
		if (isZero()) {
			return "0";
		}
		if (isIntegerInternal()) {
			return numerator.toPlainString();
		}
		return toBigDecimal().toPlainString();
	}

	/**
	 * Returns the string representation of this rational number in the form "numerator/denominator".
	 * 
	 * <p>The resulting string is a valid input of the {@link #valueOf(String)} method.</p>
	 * 
	 * <p>Examples:</p>
	 * <ul>
	 * <li><code>BigRational.valueOf(0.5).toRationalString()</code> returns <code>"1/2"</code></li>
	 * <li><code>BigRational.valueOf(2).toRationalString()</code> returns <code>"2"</code></li>
	 * <li><code>BigRational.valueOf(4, 4).toRationalString()</code> returns <code>"4/4"</code> (not reduced)</li>
	 * </ul>
	 * 
	 * @return the rational number string representation in the form "numerator/denominator", or "0" if the rational number is 0.
	 * @see #valueOf(String) 
	 * @see #valueOf(int, int) 
	 */
	public String toRationalString() {
		if (isZero()) {
			return "0";
		}
		if (isIntegerInternal()) {
			return numerator.toString();
		}
		return numerator + "/" + denominator;
	}

	/**
	 * Returns the string representation of this rational number as integer and fraction parts in the form "integerPart fractionNominator/fractionDenominator".
	 * 
	 * <p>The integer part is omitted if it is 0 (when this absolute rational number is smaller than 1).</p>
	 * <p>The fraction part is omitted it it is 0 (when this rational number is an integer).</p>
	 * <p>If this rational number is 0, then "0" is returned.</p>
	 * 
	 * <p>Example: <code>BigRational.valueOf(3.5).toIntegerRationalString()</code> returns <code>"3 1/2"</code>.</p>
	 * 
	 * @return the integer and fraction rational string representation
	 * @see #valueOf(int, int, int)
	 */
	public String toIntegerRationalString() {
		BigDecimal fractionNumerator = numerator.remainder(denominator);
		BigDecimal integerNumerator = numerator.subtract(fractionNumerator);
		BigDecimal integerPart = integerNumerator.divide(denominator);

		StringBuilder result = new StringBuilder();
		if (integerPart.signum() != 0) {
			result.append(integerPart);
		}
		if (fractionNumerator.signum() != 0) {
			if (result.length() > 0) {
				result.append(' ');
				result.append(fractionNumerator.abs());
			} else {
				result.append(fractionNumerator);
			}
			result.append('/');
			result.append(denominator);
		}
		if (result.length() == 0) {
			result.append('0');
		}
		
		return result.toString();
	}
	
	/**
	 * Creates a rational number of the specified int value.
	 * 
	 * @param value the int value
	 * @return the rational number
	 */
	public static BigRational valueOf(int value) {
		if (value == 0) {
			return ZERO;
		}
		if (value == 1) {
			return ONE;
		}
		return new BigRational(value);
	}

	/**
	 * Creates a rational number of the specified numerator/denominator int values.
	 * 
	 * @param numerator the numerator int value
	 * @param denominator the denominator int value (0 not allowed)
	 * @return the rational number
	 * @throws ArithmeticException if the denominator is 0 (division by zero)
	 */
	public static BigRational valueOf(int numerator, int denominator) {
		return of(BigDecimal.valueOf(numerator), BigDecimal.valueOf(denominator));
	}

	/**
	 * Creates a rational number of the specified integer and fraction parts.
	 * 
	 * <p>Useful to create numbers like 3 1/2 (= three and a half = 3.5) by calling
	 * <code>BigRational.valueOf(3, 1, 2)</code>.</p>
	 * <p>To create a negative rational only the integer part argument is allowed to be negative:
	 * to create -3 1/2 (= minus three and a half = -3.5) call <code>BigRational.valueOf(-3, 1, 2)</code>.</p> 
	 * 
	 * @param integer the integer part int value
	 * @param fractionNumerator the fraction part numerator int value (negative not allowed)
	 * @param fractionDenominator the fraction part denominator int value (0 or negative not allowed)
	 * @return the rational number
	 * @throws ArithmeticException if the fraction part denominator is 0 (division by zero),
	 * or if the fraction part numerator or denominator is negative
	 */
	public static BigRational valueOf(int integer, int fractionNumerator, int fractionDenominator) {
		if (fractionNumerator < 0 || fractionDenominator < 0) {
			throw new ArithmeticException("Negative value");
		}
		
		BigRational integerPart = valueOf(integer);
		BigRational fractionPart = valueOf(fractionNumerator, fractionDenominator);
		return integerPart.isPositive() ? integerPart.add(fractionPart) : integerPart.subtract(fractionPart);
	}

	/**
	 * Creates a rational number of the specified numerator/denominator BigInteger values.
	 * 
	 * @param numerator the numerator {@link BigInteger} value
	 * @param denominator the denominator {@link BigInteger} value (0 not allowed)
	 * @return the rational number
	 * @throws ArithmeticException if the denominator is 0 (division by zero)
	 */
	public static BigRational valueOf(BigInteger numerator, BigInteger denominator) {
		return of(new BigDecimal(numerator), new BigDecimal(denominator));
	}

	/**
	 * Creates a rational number of the specified {@link BigInteger} value.
	 * 
	 * @param value the {@link BigInteger} value
	 * @return the rational number
	 */
	public static BigRational valueOf(BigInteger value) {
		if (value.compareTo(BigInteger.ZERO) == 0) {
			return ZERO;
		}
		if (value.compareTo(BigInteger.ONE) == 0) {
			return ONE;
		}
		return valueOf(value, BigInteger.ONE);
	}

	/**
	 * Creates a rational number of the specified double value.
	 * 
	 * @param value the double value
	 * @return the rational number
	 * @throws NumberFormatException if the double value is Infinite or NaN.
	 */
	public static BigRational valueOf(double value) {
		if (value == 0.0) {
			return ZERO;
		}
		if (value == 1.0) {
			return ONE;
		}
		if (Double.isInfinite(value)) {
			throw new NumberFormatException("Infinite");
		}
		if (Double.isNaN(value)) {
			throw new NumberFormatException("NaN");
		}
		return valueOf(new BigDecimal(String.valueOf(value)));
	}

	/**
	 * Creates a rational number of the specified {@link BigDecimal} value.
	 * 
	 * @param value the double value
	 * @return the rational number
	 */
	public static BigRational valueOf(BigDecimal value) {
		if (value.compareTo(BigDecimal.ZERO) == 0) {
			return ZERO;
		}
		if (value.compareTo(BigDecimal.ONE) == 0) {
			return ONE;
		}
		
		int scale = value.scale();
		if (scale == 0) {
			return new BigRational(value, BigDecimal.ONE);
		} else if (scale < 0) {
			BigDecimal n = new BigDecimal(value.unscaledValue()).multiply(BigDecimal.ONE.movePointLeft(value.scale()));
			return new BigRational(n, BigDecimal.ONE);
		}
		else {
			BigDecimal n = new BigDecimal(value.unscaledValue());
			BigDecimal d = BigDecimal.ONE.movePointRight(value.scale());
			return new BigRational(n, d);
		}
	}


	/**
	 * Calculates a rational number using Continued fraction algorithm.
	 * <p>See: <a href="https://en.wikipedia.org/wiki/Continued_fraction">Continued fraction</a></p>
	 *
	 *
	 * @param value               decimal
	 * @param mathContext         the {@link MathContext} used for the calculation.
	 * @return the rational number
	 */
//    public static BigRational valueOf(BigDecimal value, MathContext mathContext) throws IllegalArgumentException, ArithmeticException {
//		if (value.compareTo(BigDecimal.ZERO) == 0) {
//			return ZERO;
//		}
//		if (value.compareTo(BigDecimal.ONE) == 0) {
//			return ONE;
//		}
//
//		MathContext mc = new MathContext(mathContext.getPrecision() + 6, mathContext.getRoundingMode());
//
//        BigDecimal numerator = BigDecimal.ONE;
//        BigDecimal pn2 = BigDecimal.ZERO;
//        BigDecimal denominator = BigDecimal.ZERO;
//        BigDecimal qn2 = BigDecimal.ONE;
//        BigDecimal x = value;
//
//        do {
//            BigDecimal a = x.setScale(0, RoundingMode.FLOOR);
//
//            BigDecimal aux = numerator;
//            numerator = a.multiply(numerator).add(pn2);
//
//            pn2 = aux;
//            aux = denominator;
//            denominator = a.multiply(denominator).add(qn2);
//
//            BigDecimal rationalCandidate = numerator.divide(denominator, mathContext);
////            left = value.abs().subtract(numeratorDivideDenominator.abs());
//
//
//            qn2 = aux;
//            BigDecimal xSubtractA = x.subtract(a);
//            if (xSubtractA.compareTo(BigDecimal.ZERO) == 0) {
//                break;
//            }
//            x = BigDecimal.ONE.divide(xSubtractA, mc);
//        } while (true);
//
//        return new BigRational(numerator, denominator);
//    }

//	/** The default epsilon used for convergence. */
//	public static final double DEFAULT_EPSILON = 1e-5;
//
//	/** The default iterations used for convergence. */
//	private static final int DEFAULT_MAX_ITERATIONS = 100;
//
//	/** Message for non-finite input double argument to factory constructors. */
//	private static final String NOT_FINITE = "Not finite: ";
//
//	/** The overflow limit for conversion from a double (2^31). */
//	private static final long OVERFLOW = 1L << 31;
//
//	public static BigRational valueOf(final double value,
//									  final double epsilon,
//									  final int maxDenominator,
//									  final int maxIterations) {
//		if (!Double.isFinite(value)) {
//			throw new IllegalArgumentException(NOT_FINITE + value);
//		}
//
//		// Remove sign, this is restored at the end.
//		// (Assumes the value is not zero and thus signum(value) is not zero).
//		final double absValue = Math.abs(value);
//		double r0 = absValue;
//		long a0 = (long) Math.floor(r0);
//		if (a0 > OVERFLOW) {
//			throw new ArithmeticException("ERROR_CONVERSION_OVERFLOW, value, a0, 1");
//		}
//
//		// check for (almost) integer arguments, which should not go to iterations.
//		if (r0 - a0 <= epsilon) {
//			int num = (int) a0;
//			int den = 1;
//			// Restore the sign.
//			if (Math.signum(num) != Math.signum(value)) {
//				if (num == Integer.MIN_VALUE) {
//					den = -den;
//				} else {
//					num = -num;
//				}
//			}
//			return new BigRational(BigDecimal.valueOf(num), BigDecimal.valueOf(den));
//		}
//
//		// Support 2^31 as maximum denominator.
//		// This is negative as an integer so convert to long.
//		final long maxDen = Math.abs((long) maxDenominator);
//
//		long p0 = 1;
//		long q0 = 0;
//		long p1 = a0;
//		long q1 = 1;
//
//		long p2;
//		long q2;
//
//		int n = 0;
//		boolean stop = false;
//		do {
//			++n;
//			final double r1 = 1.0 / (r0 - a0);
//			final long a1 = (long) Math.floor(r1);
//			p2 = (a1 * p1) + p0;
//			q2 = (a1 * q1) + q0;
//
//			if (Long.compareUnsigned(p2, OVERFLOW) > 0 ||
//					Long.compareUnsigned(q2, OVERFLOW) > 0) {
//				// In maxDenominator mode, fall-back to the previous valid fraction.
//				if (epsilon == 0.0) {
//					p2 = p1;
//					q2 = q1;
//					break;
//				}
//				throw new ArithmeticException("FractionException.ERROR_CONVERSION_OVERFLOW, value, p2, q2");
//			}
//
//			final double convergent = (double) p2 / (double) q2;
//			if (n < maxIterations &&
//					Math.abs(convergent - absValue) > epsilon &&
//					q2 < maxDen) {
//				p0 = p1;
//				p1 = p2;
//				q0 = q1;
//				q1 = q2;
//				a0 = a1;
//				r0 = r1;
//			} else {
//				stop = true;
//			}
//		} while (!stop);
//
//		if (n >= maxIterations) {
//			throw new ArithmeticException("FractionException.ERROR_CONVERSION, value, maxIterations");
//		}
//
//		// Use p2 / q2 or p1 / q1 if q2 has grown too large in maxDenominator mode
//		// Note: Conversion of long 2^31 to an integer will create a negative. This could
//		// be either the numerator or denominator. This is handled by restoring the sign.
//		int num;
//		int den;
//		if (q2 <= maxDen) {
//			num = (int) p2;
//			den = (int) q2;
//		} else {
//			num = (int) p1;
//			den = (int) q1;
//		}
//
//		// Restore the sign.
//		if (Math.signum(num) * Math.signum(den) != Math.signum(value)) {
//			if (num == Integer.MIN_VALUE) {
//				den = -den;
//			} else {
//				num = -num;
//			}
//		}
//
//		return new BigRational(BigDecimal.valueOf(num), BigDecimal.valueOf(den));
//	}

	public static final BigDecimal DEFAULT_EPSILON = BigDecimal.valueOf(1e-5);
	private static final BigInteger DEFAULT_MAX_ITERATIONS = BigInteger.valueOf(100);
	private static final String NOT_FINITE = "Not finite: ";
	private static final BigInteger OVERFLOW = BigInteger.ONE.shiftLeft(127);

	/*
	 * Licensed to the Apache Software Foundation (ASF) under one or more
	 * contributor license agreements.  See the NOTICE file distributed with
	 * this work for additional information regarding copyright ownership.
	 * The ASF licenses this file to You under the Apache License, Version 2.0
	 * (the "License"); you may not use this file except in compliance with
	 * the License.  You may obtain a copy of the License at
	 *
	 *      http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */
	/**
     * Create a fraction given the double value and either the maximum error
     * allowed or the maximum number of denominator digits.
     *
	 * <p>
     * The code was taken from the <a href="https://commons.apache.org/proper/commons-numbers/commons-numbers-docs/jacoco-aggregate/commons-numbers-fraction/org.apache.commons.numbers.fraction/Fraction.java.html">Fraction.java</a>
     * </p>
	 *
     * <p>
     * NOTE: This constructor is called with:
     * <ul>
     *  <li>EITHER a valid epsilon value and the maxDenominator
     *  <li>OR a valid maxDenominator value and the epsilon value set to
     *      zero (that way epsilon only has effect if there is an exact
     *      match before the maxDenominator value is reached).
     * </ul>
     * <p>
     * It has been done this way so that the same code can be reused for
     * both scenarios. However this could be confusing to users if it
     * were part of the public API and this method should therefore remain
     * PRIVATE.
     * </p>
     *
     * <p>
     * Warning: This conversion assumes the value is not zero.
     * </p>
     *
     * @param value Value to convert to a fraction. Must not be zero.
     * @param epsilon Maximum error allowed.
     * The resulting fraction is within {@code epsilon} of {@code value},
     * in absolute terms.
     * @param maxDenominator Maximum denominator value allowed.
     * @param maxIterations Maximum number of convergents.
     * @param mathContext the {@link MathContext} used for the calculation.
     * @throws IllegalArgumentException if the given {@code value} is NaN or infinite.
     * @throws ArithmeticException if the continued fraction failed to converge.
     */
	public static BigRational valueOf(BigDecimal value,
									  BigDecimal epsilon,
									  BigInteger maxDenominator,
									  int maxIterations,
									  MathContext mathContext) {
		if (value.compareTo(BigDecimal.ZERO) == 0) {
			return ZERO;
		}
		if (maxIterations < 1) {
			throw new IllegalArgumentException("Max iterations must be strictly positive: " + maxIterations);
		}
//		if (epsilon.compareTo(BigDecimal.ONE) >= 0) {
//			return new BigRational(value, epsilon, BigInteger.MIN_VALUE, maxIterations, mathContext);
//		}
//		throw new IllegalArgumentException("Epsilon must be positive: " + maxIterations);

		if (value.scale() == Integer.MAX_VALUE) {
			throw new IllegalArgumentException(NOT_FINITE + value);
		}

		BigDecimal absValue = value.abs();
		BigDecimal r0 = absValue;
		BigInteger a0 = r0.toBigInteger();

		if (a0.compareTo(OVERFLOW) > 0) {
			throw new IllegalArgumentException("ERROR_CONVERSION_OVERFLOW, value, a0, 1");
		}

		if (r0.subtract(new BigDecimal(a0)).compareTo(epsilon) <= 0) {
            BigInteger den = BigInteger.ONE;
			return new BigRational(new BigDecimal(a0), new BigDecimal(den));
		}

		BigInteger maxDen = maxDenominator.abs();
		BigInteger p0 = BigInteger.ONE;
		BigInteger q0 = BigInteger.ZERO;
		BigInteger p1 = a0;
		BigInteger q1 = BigInteger.ONE;

		BigInteger p2;
		BigInteger q2;
		int n = 0;
		boolean stop = false;

		do {
			n = n + 1;
			BigDecimal r1 = BigDecimal.ONE.divide(r0.subtract(new BigDecimal(a0)), mathContext);
			BigInteger a1 = r1.toBigInteger();
			p2 = a1.multiply(p1).add(p0);
			q2 = a1.multiply(q1).add(q0);

			if (p2.compareTo(OVERFLOW) > 0 || q2.compareTo(OVERFLOW) > 0) {
				if (epsilon.compareTo(BigDecimal.ZERO) == 0) {
					p2 = p1;
					q2 = q1;
					break;
				}
				throw new RuntimeException("FractionException.ERROR_CONVERSION_OVERFLOW, value, p2, q2");
			}

			BigDecimal convergent = new BigDecimal(p2).divide(new BigDecimal(q2), mathContext);
			if (n < maxIterations && convergent.subtract(absValue).abs().compareTo(epsilon) > 0 && q2.compareTo(maxDen) < 0) {
				p0 = p1;
				p1 = p2;
				q0 = q1;
				q1 = q2;
				a0 = a1;
				r0 = r1;
			} else {
				stop = true;
			}
		} while (!stop);

		if (n >= maxIterations) {
			throw new RuntimeException("FractionException.ERROR_CONVERSION, value, maxIterations");
		}

		BigInteger num;
		BigInteger den;
		if (q2.compareTo(maxDen) <= 0) {
			num = p2;
			den = q2;
		} else {
			num = p1;
			den = q1;
		}

		return new BigRational(new BigDecimal(num), new BigDecimal(den));
	}

	/**
	 * Creates a rational number of the specified string representation.
	 * 
	 * <p>The accepted string representations are:</p>
	 * <ul>
	 * <li>Output of {@link BigRational#toString()} : "integerPart.fractionPart"</li>
	 * <li>Output of {@link BigRational#toRationalString()} : "numerator/denominator"</li>
	 * <li>Output of <code>toString()</code> of {@link BigDecimal}, {@link BigInteger}, {@link Integer}, ...</li>
	 * <li>Output of <code>toString()</code> of {@link Double}, {@link Float} - except "Infinity", "-Infinity" and "NaN"</li>
	 * </ul>
	 * 
	 * @param string the string representation to convert
	 * @return the rational number
	 * @throws ArithmeticException if the denominator is 0 (division by zero)
	 */
	public static BigRational valueOf(String string) {
		String[] strings = string.split("/");
		BigRational result = valueOfSimple(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			result = result.divide(valueOfSimple(strings[i]));
		}
		return result;
	}

	private static BigRational valueOfSimple(String string) {
		return valueOf(new BigDecimal(string));
	}

	public static BigRational valueOf(boolean positive, String integerPart, String fractionPart, String fractionRepeatPart, String exponentPart) {
		BigRational result = ZERO;

		if (fractionRepeatPart != null && fractionRepeatPart.length() > 0) {
			BigInteger lotsOfNines = BigInteger.TEN.pow(fractionRepeatPart.length()).subtract(BigInteger.ONE);
			result = valueOf(new BigInteger(fractionRepeatPart), lotsOfNines);
		}

		if (fractionPart != null && fractionPart.length() > 0) {
			result = result.add(valueOf(new BigInteger(fractionPart)));
			result = result.divide(BigInteger.TEN.pow(fractionPart.length()));
		}

		if (integerPart != null && integerPart.length() > 0) {
			result = result.add(new BigInteger(integerPart));
		}

		if (exponentPart != null && exponentPart.length() > 0) {
			int exponent = Integer.parseInt(exponentPart);
			BigInteger powerOfTen = BigInteger.TEN.pow(Math.abs(exponent));
			result = exponent >= 0 ? result.multiply(powerOfTen) : result.divide(powerOfTen);
		}

		if (!positive) {
			result = result.negate();
		}

		return result;
	}

	/**
	 * Creates a rational number of the specified numerator/denominator BigDecimal values.
	 * 
	 * @param numerator the numerator {@link BigDecimal} value
	 * @param denominator the denominator {@link BigDecimal} value (0 not allowed)
	 * @return the rational number
	 * @throws ArithmeticException if the denominator is 0 (division by zero)
	 */
	public static BigRational valueOf(BigDecimal numerator, BigDecimal denominator) {
		return valueOf(numerator).divide(valueOf(denominator));
	}
	
	private static BigRational of(BigDecimal numerator, BigDecimal denominator) {
		if (numerator.signum() == 0 && denominator.signum() != 0) {
			return ZERO;
		}
		if (numerator.compareTo(BigDecimal.ONE) == 0 && denominator.compareTo(BigDecimal.ONE) == 0) {
			return ONE;
		}
		return new BigRational(numerator, denominator);
	}

	/**
	 * Returns the smallest of the specified rational numbers.
	 * 
	 * @param values the rational numbers to compare
	 * @return the smallest rational number, 0 if no numbers are specified
	 */
	public static BigRational min(BigRational... values) {
		if (values.length == 0) {
			return BigRational.ZERO;
		}
		BigRational result = values[0];
		for (int i = 1; i < values.length; i++) {
			result = result.min(values[i]);
		}
		return result;
	}

	/**
	 * Returns the largest of the specified rational numbers.
	 * 
	 * @param values the rational numbers to compare
	 * @return the largest rational number, 0 if no numbers are specified
	 * @see #max(BigRational)
	 */
	public static BigRational max(BigRational... values) {
		if (values.length == 0) {
			return BigRational.ZERO;
		}
		BigRational result = values[0];
		for (int i = 1; i < values.length; i++) {
			result = result.max(values[i]);
		}
		return result;
	}

	private static List<BigRational> bernoulliCache = new ArrayList<>();
	
	/**
	 * Calculates the Bernoulli number for the specified index.
	 * 
	 * <p>This function calculates the <strong>first Bernoulli numbers</strong> and therefore <code>bernoulli(1)</code> returns -0.5</p>
	 * <p>Note that <code>bernoulli(x)</code> for all odd x &gt; 1 returns 0</p>
	 * <p>See: <a href="https://en.wikipedia.org/wiki/Bernoulli_number">Wikipedia: Bernoulli number</a></p>
	 * 
	 * @param n the index of the Bernoulli number to be calculated (starting at 0)
	 * @return the Bernoulli number for the specified index
	 * @throws ArithmeticException if x is lesser than 0
	 */
    public static BigRational bernoulli(int n) {
		if (n < 0) {
			throw new ArithmeticException("Illegal bernoulli(n) for n < 0: n = " + n);
		}
    	if (n == 1) {
    		return valueOf(-1, 2);
    	} else if (n % 2 == 1) {
    		return ZERO;
    	}
    	
    	synchronized (bernoulliCache) {
    		int index = n / 2;
    		
    		if (bernoulliCache.size() <= index) {
    			for (int i = bernoulliCache.size(); i <= index; i++) {
    				BigRational b = calculateBernoulli(i * 2);
					bernoulliCache.add(b);
				}
    		}
    		
    		return bernoulliCache.get(index);
		}
    }
    
    private static BigRational calculateBernoulli(int n) {
    	return IntStream.rangeClosed(0, n).parallel().mapToObj(k -> {
            BigRational jSum = ZERO ;
            BigRational bin = ONE ;
            for(int j=0 ; j <= k ; j++) {
                BigRational jPowN = valueOf(j).pow(n);
                if (j % 2 == 0) {
                	jSum = jSum.add(bin.multiply(jPowN)) ;
                } else {
                	jSum = jSum.subtract(bin.multiply(jPowN)) ;
                }

                bin = bin.multiply(valueOf(k-j).divide(valueOf(j+1)));
            }
            return jSum.divide(valueOf(k+1));
    	}).reduce(ZERO, BigRational::add);
    }

	@Override
	public int intValue() {
		return toBigDecimal().intValue();
	}

	@Override
	public long longValue() {
		return toBigDecimal().longValue();
	}

	@Override
	public float floatValue() {
		return toFloat();
	}

	@Override
	public double doubleValue() {
		return toDouble();
	}
}
