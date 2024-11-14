package ch.obermuhlner.math.big;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalFastMathAddSubtractTest {
    private static final MathContext DECIMAL_7_DIGITS = MathContext.DECIMAL32;
    
    @Test
    public void add_positive_positive() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("1");
        
        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654322"), actual);
    }

    @Test
    public void add_positive_positive_secondSmallOk1() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("21");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765434E+7"), actual);
    }

    @Test
    public void add_positive_positive_firstSmallOk1() {
        BigDecimal a = new BigDecimal("21");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765434E+7"), actual);
    }

    @Test
    public void add_positive_positive_secondSmallOk2() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("32109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876546E+19"), actual);
    }

    @Test
    public void add_positive_positive_firstSmallOk2() {
        BigDecimal a = new BigDecimal("32109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876546E+19"), actual);
    }

    @Test
    public void add_positive_positive_secondTooSmall() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("0.1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void add_positive_positive_firstTooSmall() {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("7654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void add_positive_positive_secondTooSmall2() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void add_positive_positive_firstTooSmall2() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void add_positive_positive_secondTooSmall3() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("2109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void add_positive_positive_firstTooSmall3() {
        BigDecimal a = new BigDecimal("2109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void add_positive_negative() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654320"), actual);
    }

    @Test
    public void add_positive_negative_secondSmallOk1() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("-21");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765430E+7"), actual);
    }

    @Test
    public void add_positive_negative_firstSmallOk1() {
        BigDecimal a = new BigDecimal("21");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765430E+7"), actual);
    }

    @Test
    public void add_positive_negative_secondSmallOk2() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("-32109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876540E+19"), actual);
    }

    @Test
    public void add_positive_negative_firstSmallOk2() {
        BigDecimal a = new BigDecimal("32109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876540E+19"), actual);
    }

    @Test
    public void add_positive_negative_secondTooSmall() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("-0.1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void add_positive_negative_firstTooSmall() {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("-7654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void add_positive_negative_secondTooSmall2() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void add_positive_negative_firstTooSmall2() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void add_positive_negative_secondTooSmall3() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("-2109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void add_positive_negative_firstTooSmall3() {
        BigDecimal a = new BigDecimal("2109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void add_negative_negative() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654322"), actual);
    }

    @Test
    public void add_negative_negative_secondSmallOk1() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("-21");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765434E+7"), actual);
    }

    @Test
    public void add_negative_negative_firstSmallOk1() {
        BigDecimal a = new BigDecimal("-21");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765434E+7"), actual);
    }

    @Test
    public void add_negative_negative_secondSmallOk2() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("-32109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876546E+19"), actual);
    }

    @Test
    public void add_negative_negative_firstSmallOk2() {
        BigDecimal a = new BigDecimal("-32109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876546E+19"), actual);
    }

    @Test
    public void add_negative_negative_secondTooSmall() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("-0.1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void add_negative_negative_firstTooSmall() {
        BigDecimal a = new BigDecimal("-0.1");
        BigDecimal b = new BigDecimal("-7654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void add_negative_negative_secondTooSmall2() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void add_negative_negative_firstTooSmall2() {
        BigDecimal a = new BigDecimal("-1");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void add_negative_negative_secondTooSmall3() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("-2109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void add_negative_negative_firstTooSmall3() {
        BigDecimal a = new BigDecimal("-2109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void add_negative_positive() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654320"), actual);
    }

    @Test
    public void add_negative_positive_secondSmallOk1() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("21");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765430E+7"), actual);
    }

    @Test
    public void add_negative_positive_firstSmallOk1() {
        BigDecimal a = new BigDecimal("-21");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765430E+7"), actual);
    }

    @Test
    public void add_negative_positive_secondSmallOk2() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("32109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876540E+19"), actual);
    }

    @Test
    public void add_negative_positive_firstSmallOk2() {
        BigDecimal a = new BigDecimal("-32109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876540E+19"), actual);
    }

    @Test
    public void add_negative_positive_secondTooSmall() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("0.1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void add_negative_positive_firstTooSmall() {
        BigDecimal a = new BigDecimal("-0.1");
        BigDecimal b = new BigDecimal("7654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void add_negative_positive_secondTooSmall2() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void add_negative_positive_firstTooSmall2() {
        BigDecimal a = new BigDecimal("-1");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void add_negative_positive_secondTooSmall3() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("2109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void add_negative_positive_firstTooSmall3() {
        BigDecimal a = new BigDecimal("-2109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.add(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void subtract_positive_positive() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654320"), actual);
    }

    @Test
    public void subtract_positive_positive_secondSmallOk1() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("21");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765430E+7"), actual);
    }

    @Test
    public void subtract_positive_positive_firstSmallOk1() {
        BigDecimal a = new BigDecimal("21");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765430E+7"), actual);
    }

    @Test
    public void subtract_positive_positive_secondSmallOk2() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("32109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876540E+19"), actual);
    }

    @Test
    public void subtract_positive_positive_firstSmallOk2() {
        BigDecimal a = new BigDecimal("32109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876540E+19"), actual);
    }

    @Test
    public void subtract_positive_positive_secondTooSmall() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("0.1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void subtract_positive_positive_firstTooSmall() {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("7654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void subtract_positive_positive_secondTooSmall2() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void subtract_positive_positive_firstTooSmall2() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void subtract_positive_positive_secondTooSmall3() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("2109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void subtract_positive_positive_firstTooSmall3() {
        BigDecimal a = new BigDecimal("2109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void subtract_positive_negative() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654322"), actual);
    }

    @Test
    public void subtract_positive_negative_secondSmallOk1() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("-21");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765434E+7"), actual);
    }

    @Test
    public void subtract_positive_negative_firstSmallOk1() {
        BigDecimal a = new BigDecimal("21");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765434E+7"), actual);
    }

    @Test
    public void subtract_positive_negative_secondSmallOk2() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("-32109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876546E+19"), actual);
    }

    @Test
    public void subtract_positive_negative_firstSmallOk2() {
        BigDecimal a = new BigDecimal("32109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876546E+19"), actual);
    }

    @Test
    public void subtract_positive_negative_secondTooSmall() {
        BigDecimal a = new BigDecimal("7654321");
        BigDecimal b = new BigDecimal("-0.1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void subtract_positive_negative_firstTooSmall() {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("-7654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void subtract_positive_negative_secondTooSmall2() {
        BigDecimal a = new BigDecimal("87654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void subtract_positive_negative_firstTooSmall2() {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void subtract_positive_negative_secondTooSmall3() {
        BigDecimal a = new BigDecimal("98765432109876543210");
        BigDecimal b = new BigDecimal("-2109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void subtract_positive_negative_firstTooSmall3() {
        BigDecimal a = new BigDecimal("2109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void subtract_negative_negative() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654320"), actual);
    }

    @Test
    public void subtract_negative_negative_secondSmallOk1() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("-21");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765430E+7"), actual);
    }

    @Test
    public void subtract_negative_negative_firstSmallOk1() {
        BigDecimal a = new BigDecimal("-21");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765430E+7"), actual);
    }

    @Test
    public void subtract_negative_negative_secondSmallOk2() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("-32109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876540E+19"), actual);
    }

    @Test
    public void subtract_negative_negative_firstSmallOk2() {
        BigDecimal a = new BigDecimal("-32109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876540E+19"), actual);
    }

    @Test
    public void subtract_negative_negative_secondTooSmall() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("-0.1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void subtract_negative_negative_firstTooSmall() {
        BigDecimal a = new BigDecimal("-0.1");
        BigDecimal b = new BigDecimal("-7654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("7654321"), actual);
    }

    @Test
    public void subtract_negative_negative_secondTooSmall2() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("-1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void subtract_negative_negative_firstTooSmall2() {
        BigDecimal a = new BigDecimal("-1");
        BigDecimal b = new BigDecimal("-87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("8.765432E+7"), actual);
    }

    @Test
    public void subtract_negative_negative_secondTooSmall3() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("-2109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void subtract_negative_negative_firstTooSmall3() {
        BigDecimal a = new BigDecimal("-2109876543210");
        BigDecimal b = new BigDecimal("-98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("9.876543E+19"), actual);
    }

    @Test
    public void subtract_negative_positive() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654322"), actual);
    }

    @Test
    public void subtract_negative_positive_secondSmallOk1() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("21");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765434E+7"), actual);
    }

    @Test
    public void subtract_negative_positive_firstSmallOk1() {
        BigDecimal a = new BigDecimal("-21");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765434E+7"), actual);
    }

    @Test
    public void subtract_negative_positive_secondSmallOk2() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("32109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876546E+19"), actual);
    }

    @Test
    public void subtract_negative_positive_firstSmallOk2() {
        BigDecimal a = new BigDecimal("-32109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876546E+19"), actual);
    }

    @Test
    public void subtract_negative_positive_secondTooSmall() {
        BigDecimal a = new BigDecimal("-7654321");
        BigDecimal b = new BigDecimal("0.1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void subtract_negative_positive_firstTooSmall() {
        BigDecimal a = new BigDecimal("-0.1");
        BigDecimal b = new BigDecimal("7654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-7654321"), actual);
    }

    @Test
    public void subtract_negative_positive_secondTooSmall2() {
        BigDecimal a = new BigDecimal("-87654321");
        BigDecimal b = new BigDecimal("1");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void subtract_negative_positive_firstTooSmall2() {
        BigDecimal a = new BigDecimal("-1");
        BigDecimal b = new BigDecimal("87654321");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-8.765432E+7"), actual);
    }

    @Test
    public void subtract_negative_positive_secondTooSmall3() {
        BigDecimal a = new BigDecimal("-98765432109876543210");
        BigDecimal b = new BigDecimal("2109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void subtract_negative_positive_firstTooSmall3() {
        BigDecimal a = new BigDecimal("-2109876543210");
        BigDecimal b = new BigDecimal("98765432109876543210");

        BigDecimal actual = BigDecimalMath.subtract(a, b, DECIMAL_7_DIGITS);
        assertEquals(new BigDecimal("-9.876543E+19"), actual);
    }

    @Test
    public void subtract_returnRoundedMinuend() {
        BigDecimal a = new BigDecimal("3.142");
        BigDecimal b = new BigDecimal("0.5559");

        BigDecimal actual = BigDecimalMath.subtract(a, b, new MathContext(1, RoundingMode.HALF_EVEN));
        assertEquals(new BigDecimal("3"), actual);
    }

    @Test
    public void subtract_returnRoundedSubtrahend() {
        BigDecimal a = new BigDecimal("0.5559");
        BigDecimal b = new BigDecimal("3.142");

        BigDecimal actual = BigDecimalMath.subtract(a, b, new MathContext(1, RoundingMode.HALF_EVEN));
        assertEquals(new BigDecimal("-3"), actual);
    }
}