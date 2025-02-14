package io.github.runforlife.bigdecimal.math;

import java.math.BigInteger;
import java.util.Objects;

public class BigIntegerFraction {
//    private boolean positive;
    private BigInteger numerator;
    private BigInteger denominator;

    public BigIntegerFraction() {
    }

//    public boolean isPositive() {
//        return positive;
//    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public BigIntegerFraction(/*boolean positive, */BigInteger numerator, BigInteger denominator) {
//        this.positive = positive;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BigIntegerFraction that = (BigIntegerFraction) o;
        return Objects.equals(numerator, that.numerator) && Objects.equals(denominator, that.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        return "BigIntegerFraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
