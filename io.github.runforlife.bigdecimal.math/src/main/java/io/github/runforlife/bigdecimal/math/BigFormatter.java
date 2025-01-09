package io.github.runforlife.bigdecimal.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigFormatter {
    private static final int SIGN_SYMBOL_LENGTH = 1;
    private static final int ZERO_SYMBOL_LENGTH = 1;
    private static final int DOT_SYMBOL_LENGTH = 1;
    private static final int EXPONENT_SYMBOL_LENGTH = 1;

    public static String format(BigDecimal value, int numberOfCharacters, RoundingMode roundingMode) {
        BigDecimal valueWithoutTrailingZeros = value.stripTrailingZeros();
        int scale = valueWithoutTrailingZeros.scale();
        int precision = valueWithoutTrailingZeros.precision();

        if (valueWithoutTrailingZeros.compareTo(BigDecimal.ZERO) > 0) {
            if (scale > 0) {
                if (precision > 1) {
                    int numberOfSymbols = scale + precision;
                    if (numberOfSymbols <= numberOfCharacters) {
                        return String.valueOf(valueWithoutTrailingZeros);
                    } else {
                        int scaleNumberOfDigits = IntLongUtils.numberOfDigits(scale);

                        StringBuilder pattern = new StringBuilder("0.");
                        for (int i = 0; i < numberOfCharacters - DOT_SYMBOL_LENGTH - SIGN_SYMBOL_LENGTH - EXPONENT_SYMBOL_LENGTH - scaleNumberOfDigits; i++) {
                            pattern.append("#");
                        }
                        pattern.append("E0");
                        String patternString = pattern.toString();

                        DecimalFormat formatter = new DecimalFormat(patternString, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                        formatter.setRoundingMode(roundingMode);
                        formatter.setMinimumFractionDigits(0);
                        String formattedValue = formatter.format(value);

                        return formattedValue;
                    }
                } else {
                    int numberOfSymbols = DOT_SYMBOL_LENGTH + precision + scale;
                    if (numberOfSymbols <= numberOfCharacters) {
                        return String.valueOf(valueWithoutTrailingZeros);
                    } else {
                        int scaleNumberOfDigits = IntLongUtils.numberOfDigits(scale);

                        StringBuilder pattern = new StringBuilder("0.");
                        for (int i = 0; i < numberOfCharacters - DOT_SYMBOL_LENGTH - SIGN_SYMBOL_LENGTH - EXPONENT_SYMBOL_LENGTH - scaleNumberOfDigits; i++) {
                            pattern.append("#");
                        }
                        pattern.append("E0");
                        String patternString = pattern.toString();

                        DecimalFormat formatter = new DecimalFormat(patternString, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                        formatter.setRoundingMode(roundingMode);
                        formatter.setMinimumFractionDigits(0);
                        String formattedValue = formatter.format(value);

                        return formattedValue;
                    }
                }
//                StringBuilder pattern = new StringBuilder("0.");
//                for (int i = 0; i < numberOfCharacters - 2; i++) {
//                    pattern.append("#");
//                }
//                DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                formatter.setRoundingMode(roundingMode);
//                formatter.setMinimumFractionDigits(0);
//                String formattedValue = formatter.format(valueWithoutTrailingZeros);
//
//                return formattedValue;
            } else if (scale < 0) {
                int numberOfScaleDigits = IntLongUtils.numberOfDigits(scale);

                int numberOfSymbols = precision - scale;
                if (numberOfSymbols <= numberOfCharacters) {
                    BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(0, roundingMode);
                    return String.valueOf(valueFormatted);
                }

                return String.valueOf(valueWithoutTrailingZeros);
//                StringBuilder pattern = new StringBuilder("0.");
//                for (int i = 0; i < numberOfCharacters - 3; i++) {
//                    pattern.append("#");
//                }
//                DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                formatter.setRoundingMode(roundingMode);
//                formatter.setMinimumFractionDigits(0);
//                String formattedValue = formatter.format(valueWithoutTrailingZeros);
//
//                if (formattedValue.equals("-0")) {
//                    return "0";
//                }
//
//                return formattedValue;
            } else {
                return String.valueOf(valueWithoutTrailingZeros);

//                int numberOfDigits = IntLongUtils.numberOfDigits(scale);
////                if (precision )
//
//                StringBuilder pattern = new StringBuilder("0.");
//                for (int i = 0; i < numberOfCharacters - 2; i++) {
//                    pattern.append("#");
//                }
//                DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                formatter.setRoundingMode(roundingMode);
//                formatter.setMinimumFractionDigits(0);
//                String formattedValue = formatter.format(valueWithoutTrailingZeros);
//
//                return formattedValue;
            }
        } else if (valueWithoutTrailingZeros.compareTo(BigDecimal.ZERO) < 0) {
            if (scale > 0) {
                if (scale > precision) {
                    if (SIGN_SYMBOL_LENGTH + DOT_SYMBOL_LENGTH + precision + scale <= numberOfCharacters) {
                        return String.valueOf(valueWithoutTrailingZeros);
                    } else {
//                        double numberOfVisibleSymbols = precision - scale - SIGN_SYMBOL_LENGTH - DOT_SYMBOL_LENGTH;
//                        double numberOfAllSymbols = SIGN_SYMBOL_LENGTH + ZERO_SYMBOL_LENGTH + DOT_SYMBOL_LENGTH + (scale - precision) + precision;
                        int numberOfVisibleSymbolsWithoutExponenta = numberOfCharacters - (SIGN_SYMBOL_LENGTH + ZERO_SYMBOL_LENGTH + DOT_SYMBOL_LENGTH + (scale - precision));

                        int exponenta = scale - precision + 1;
                        int numberOfExponentaDigits = IntLongUtils.numberOfDigits(exponenta);
                        int numberOfVisibleSymbolsWithExponenta = numberOfCharacters - (SIGN_SYMBOL_LENGTH + DOT_SYMBOL_LENGTH + EXPONENT_SYMBOL_LENGTH + SIGN_SYMBOL_LENGTH + numberOfExponentaDigits);

                        if (numberOfVisibleSymbolsWithoutExponenta >= numberOfVisibleSymbolsWithExponenta) {
                            int newScale = precision - (SIGN_SYMBOL_LENGTH + ZERO_SYMBOL_LENGTH + DOT_SYMBOL_LENGTH + (scale - precision));
                            BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(newScale, roundingMode);
                            String s = String.valueOf(valueFormatted);
                            return s;
                        } else {

                            int scaleNumberOfDigits = IntLongUtils.numberOfDigits(scale);

                            StringBuilder pattern = new StringBuilder("0.");
                            for (int i = 0; i < numberOfCharacters - SIGN_SYMBOL_LENGTH - DOT_SYMBOL_LENGTH - SIGN_SYMBOL_LENGTH - EXPONENT_SYMBOL_LENGTH - scaleNumberOfDigits; i++) {
                                pattern.append("#");
                            }
                            pattern.append("E0");
                            String patternString = pattern.toString();

                            DecimalFormat formatter = new DecimalFormat(patternString, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                            formatter.setRoundingMode(roundingMode);
                            formatter.setMinimumFractionDigits(0);
                            String formattedValue = formatter.format(value);

                            return formattedValue;
                        }
                    }
                } else {
                    int numberOfSymbols = precision + scale;
                    if (numberOfSymbols <= numberOfCharacters) {
//                    BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(0, roundingMode);
//                    return String.valueOf(valueFormatted);
                        return String.valueOf(valueWithoutTrailingZeros);
                    }

                    int numberOfScaleDigits = IntLongUtils.numberOfDigits(scale);

                    if (precision - numberOfScaleDigits + 1 <= numberOfCharacters) {
                        System.out.println();
                    }

                    return String.valueOf(valueWithoutTrailingZeros);
                }
//                StringBuilder pattern = new StringBuilder("0.");
//                for (int i = 0; i < numberOfCharacters - 3; i++) {
//                    pattern.append("#");
//                }
//                DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                formatter.setRoundingMode(roundingMode);
//                formatter.setMinimumFractionDigits(0);
//                String formattedValue = formatter.format(valueWithoutTrailingZeros);
//
//                if (formattedValue.equals("-0")) {
//                    return "0";
//                }
//
//                return formattedValue;
            } else if (scale < 0) {
                int numberOfSymbols1 = SIGN_SYMBOL_LENGTH + precision - scale;
                if (numberOfSymbols1 <= numberOfCharacters) {
                    BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(0, roundingMode);
                    return String.valueOf(valueFormatted);
//                    return String.valueOf(valueWithoutTrailingZeros);
                }

                int numberOfSymbols2 = SIGN_SYMBOL_LENGTH + precision - scale;
                if (numberOfSymbols2 < numberOfCharacters) {
                    BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(0, roundingMode);
                    return String.valueOf(valueFormatted);
                }

                return String.valueOf(valueWithoutTrailingZeros);
//                StringBuilder pattern = new StringBuilder("0.");
//                for (int i = 0; i < numberOfCharacters - 3; i++) {
//                    pattern.append("#");
//                }
//                DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                formatter.setRoundingMode(roundingMode);
//                formatter.setMinimumFractionDigits(0);
//                String formattedValue = formatter.format(valueWithoutTrailingZeros);
//
//                if (formattedValue.equals("-0")) {
//                    return "0";
//                }
//
//                return formattedValue;
            } else {
                return String.valueOf(valueWithoutTrailingZeros);
//                StringBuilder pattern = new StringBuilder("0.");
//                for (int i = 0; i < numberOfCharacters - 3; i++) {
//                    pattern.append("#");
//                }
//                DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                formatter.setRoundingMode(roundingMode);
//                formatter.setMinimumFractionDigits(0);
//                String formattedValue = formatter.format(valueWithoutTrailingZeros);
//
//                if (formattedValue.equals("-0")) {
//                    return "0";
//                }
//
//                return formattedValue;
            }
        } else {
            return "0";
        }


//        StringBuilder pattern = new StringBuilder("0.");
//        for (int i = 0; i < numberOfCharacters - 3; i++) {
//            pattern.append("#");
//        }
//        DecimalFormat formatter = new DecimalFormat(pattern.toString(), DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//        formatter.setRoundingMode(roundingMode);
//        formatter.setMinimumFractionDigits(0);
//        String format = formatter.format(number);
    }

    public static String format(BigInteger value, int numberOfCharacters, RoundingMode roundingMode) {
        return BigFormatter.format(new BigDecimal(value), numberOfCharacters, roundingMode);
    }

    private BigFormatter() {
    }
}
