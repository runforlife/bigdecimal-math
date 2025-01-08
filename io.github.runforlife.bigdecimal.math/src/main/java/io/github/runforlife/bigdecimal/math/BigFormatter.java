package io.github.runforlife.bigdecimal.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigFormatter {
    private static final int DOT_SYMBOL_LENGTH = 1;
    private static final int SIGN_SYMBOL_LENGTH = 1;
    private static final int EXPONENT_SYMBOL_LENGTH = 1;

    public static String format(BigDecimal value, int numberOfCharacters, RoundingMode roundingMode) {
//        if (number.compareTo(BigDecimal.ZERO) == 0) {
//            return "0";
//        }
//
//
//        //TODO this does not work because scale is: unscaled * 10 * scale
////        if (number.scale() >= -33 && number.scale() <= 34) {
////            number = number.setScale(34, roundingMode);
////        }
//
//
//        String numberAsString = number.toString();
//        while (numberAsString.contains("0E") || numberAsString.contains(".E")) {
//            numberAsString = numberAsString.replace("0E", "E");
//            numberAsString = numberAsString.replace(".E", "E");
//        }
//        if (numberAsString.contains(".") && !numberAsString.contains("E")) {
//            while ((numberAsString.length() > 1 && numberAsString.contains(".")) && (numberAsString.charAt(numberAsString.length() - 1) == '0' || numberAsString.charAt(numberAsString.length() - 1) == '.')) {
//                numberAsString = numberAsString.substring(0, numberAsString.length() - 1);
//            }
//        }
//        int length = numberAsString.length();
//        if (length <= numberOfCharacters) {
//            return numberAsString;
//        } else {
//            BigDecimal numberStripTrailingZeros = number.stripTrailingZeros();
//            String numberStripTrailingZerosAsString = numberStripTrailingZeros.toString();
//            int numberStripTrailingZerosLength = numberStripTrailingZerosAsString.length();
//            if (numberStripTrailingZerosLength <= numberOfCharacters) {
//                return numberStripTrailingZerosAsString;
//            } else {
//                if (number.abs().compareTo(BigDecimal.ONE) > 0) {
//                    int scale = number.scale();
//
//                    BigDecimal number2 = number;
//                    String string1 = number2.toString();
//                    length = string1.length();
//                    int newScale = scale;
//                    while (length > numberOfCharacters) {
//                        newScale = newScale - 1;
//                        number2 = number2.setScale(newScale, roundingMode);
//                        number2 = number2.stripTrailingZeros();
//                        string1 = number2.toString();
//                        length = string1.length();
//                    }
//
//                    return string1;
//                } else if (number.abs().compareTo(BigDecimal.ONE) < 0) {
//                    if (number.compareTo(BigDecimal.ZERO) > 0) {
//                        int precision = number.precision();
//                        int scale = number.scale();
//                        int diff = precision - scale;
//                        if (diff < -2) {
//                            String pattern = "0.";
//                            for (int i = 0; i < numberOfCharacters - 5; i++) {
//                                pattern = pattern + "#";
//                            }
//                            pattern = pattern + "E0";
//
//                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                            formatter.setRoundingMode(roundingMode);
//                            formatter.setMinimumFractionDigits(0);
//                            String format = formatter.format(number);
//
//                            while (format.length() > numberOfCharacters) {
//                                pattern = pattern.replace(".#", ".");
//                                pattern = pattern.replace(".E", "E");
//                                formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                                formatter.setRoundingMode(roundingMode);
//                                formatter.setMinimumFractionDigits(0);
//                                format = formatter.format(number);
//                            }
//
//                            return format;
//                        } else {
//                            String pattern = "0.";
//                            for (int i = 0; i < numberOfCharacters - 2; i++) {
//                                pattern = pattern + "#";
//                            }
//
//                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                            formatter.setRoundingMode(roundingMode);
//                            formatter.setMinimumFractionDigits(0);
//                            String format = formatter.format(number);
//                            return format;
//                        }
//                    } else {
//                        int precision = number.precision();
//                        int scale = number.scale();
//                        int diff = precision - scale;
//                        if (diff < -2) {
//                            String pattern = "0.";
//                            for (int i = 0; i < numberOfCharacters - 6; i++) {
//                                pattern = pattern + "#";
//                            }
//                            pattern = pattern + "E0";
//
//                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                            formatter.setRoundingMode(roundingMode);
//                            formatter.setMinimumFractionDigits(0);
//                            String format = formatter.format(number);
//
//                            while (format.length() > numberOfCharacters) {
//                                pattern = pattern.replace(".#", ".");
//                                pattern = pattern.replace(".E", "E");
//                                formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                                formatter.setRoundingMode(roundingMode);
//                                formatter.setMinimumFractionDigits(0);
//                                format = formatter.format(number);
//                            }
//
//                            return format;
//                        } else {
//                            String pattern = "0.";
//                            for (int i = 0; i < numberOfCharacters - 3; i++) {
//                                pattern = pattern + "#";
//                            }
//
//                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
//                            formatter.setRoundingMode(roundingMode);
//                            formatter.setMinimumFractionDigits(0);
//                            String format = formatter.format(number);
//                            return format;
//                        }
//                    }
//                }
//                return "";
//            }
//        }
        BigDecimal valueWithoutTrailingZeros = value.stripTrailingZeros();
        int scale = valueWithoutTrailingZeros.scale();
        int precision = valueWithoutTrailingZeros.precision();

        if (valueWithoutTrailingZeros.compareTo(BigDecimal.ZERO) > 0) {
            if (scale > 0) {
                if (scale > precision) {
                    if (DOT_SYMBOL_LENGTH + scale + precision <= numberOfCharacters) {
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
                    if (precision + scale <= numberOfCharacters) {
//                    BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(0, roundingMode);
//                    return String.valueOf(valueFormatted);
                        return String.valueOf(valueWithoutTrailingZeros);
                    }

                    return String.valueOf(valueWithoutTrailingZeros);
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

                if (precision - scale <= numberOfCharacters) {
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
                } else {
                    if (precision + scale <= numberOfCharacters) {
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
                if (SIGN_SYMBOL_LENGTH + precision - scale <= numberOfCharacters) {
                    BigDecimal valueFormatted = valueWithoutTrailingZeros.setScale(0, roundingMode);
                    return String.valueOf(valueFormatted);
//                    return String.valueOf(valueWithoutTrailingZeros);
                }

                if (SIGN_SYMBOL_LENGTH + precision - scale < numberOfCharacters) {
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
