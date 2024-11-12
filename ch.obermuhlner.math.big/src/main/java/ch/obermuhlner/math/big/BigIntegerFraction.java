package ch.obermuhlner.math.big;

import java.math.BigInteger;
import java.util.Objects;

public class BigIntegerFraction {
    private boolean positive;
    private BigInteger numerator;
    private BigInteger denominator;

    public BigIntegerFraction() {
    }

    public boolean isPositive() {
        return positive;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public BigIntegerFraction(boolean positive, BigInteger numerator, BigInteger denominator) {
        this.positive = positive;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigIntegerFraction that = (BigIntegerFraction) o;
        return positive == that.positive && Objects.equals(numerator, that.numerator) && Objects.equals(denominator, that.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positive, numerator, denominator);
    }

    @Override
    public String toString() {
        return "BigIntegerFraction{" +
                "positive=" + positive +
                ", numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
