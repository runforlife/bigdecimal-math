package io.github.runforlife.bigdecimal.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigFormatter {
    public static String format(BigDecimal number, int numberOfCharacters, RoundingMode roundingMode) {
        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }


        //TODO this does not work because scale is: unscaled * 10 * scale
//        if (number.scale() >= -33 && number.scale() <= 34) {
//            number = number.setScale(34, roundingMode);
//        }


        String numberAsString = number.toString();
        while (numberAsString.contains("0E") || numberAsString.contains(".E")) {
            numberAsString = numberAsString.replace("0E", "E");
            numberAsString = numberAsString.replace(".E", "E");
        }
        if (numberAsString.contains(".") && !numberAsString.contains("E")) {
            while ((numberAsString.length() > 1 && numberAsString.contains(".")) && (numberAsString.charAt(numberAsString.length() - 1) == '0' || numberAsString.charAt(numberAsString.length() - 1) == '.')) {
                numberAsString = numberAsString.substring(0, numberAsString.length() - 1);
            }
        }
        int length = numberAsString.length();
        if (length <= numberOfCharacters) {
            return numberAsString;
        } else {
            BigDecimal numberStripTrailingZeros = number.stripTrailingZeros();
            String numberStripTrailingZerosAsString = numberStripTrailingZeros.toString();
            int numberStripTrailingZerosLength = numberStripTrailingZerosAsString.length();
            if (numberStripTrailingZerosLength <= numberOfCharacters) {
                return numberStripTrailingZerosAsString;
            } else {
                if (number.abs().compareTo(BigDecimal.ONE) > 0) {
                    int scale = number.scale();

                    BigDecimal number2 = number;
                    String string1 = number2.toString();
                    length = string1.length();
                    int newScale = scale;
                    while (length > numberOfCharacters) {
                        newScale = newScale - 1;
                        number2 = number2.setScale(newScale, roundingMode);
                        number2 = number2.stripTrailingZeros();
                        string1 = number2.toString();
                        length = string1.length();
                    }

                    return string1;
                } else if (number.abs().compareTo(BigDecimal.ONE) < 0) {
                    if (number.compareTo(BigDecimal.ZERO) > 0) {
                        int precision = number.precision();
                        int scale = number.scale();
                        int diff = precision - scale;
                        if (diff < -2) {
                            String pattern = "0.";
                            for (int i = 0; i < numberOfCharacters - 5; i++) {
                                pattern = pattern + "#";
                            }
                            pattern = pattern + "E0";

                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                            formatter.setRoundingMode(roundingMode);
                            formatter.setMinimumFractionDigits(0);
                            String format = formatter.format(number);

                            while (format.length() > numberOfCharacters) {
                                pattern = pattern.replace(".#", ".");
                                pattern = pattern.replace(".E", "E");
                                formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                                formatter.setRoundingMode(roundingMode);
                                formatter.setMinimumFractionDigits(0);
                                format = formatter.format(number);
                            }

                            return format;
                        } else {
                            String pattern = "0.";
                            for (int i = 0; i < numberOfCharacters - 2; i++) {
                                pattern = pattern + "#";
                            }

                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                            formatter.setRoundingMode(roundingMode);
                            formatter.setMinimumFractionDigits(0);
                            String format = formatter.format(number);
                            return format;
                        }
                    } else {
                        int precision = number.precision();
                        int scale = number.scale();
                        int diff = precision - scale;
                        if (diff < -2) {
                            String pattern = "0.";
                            for (int i = 0; i < numberOfCharacters - 6; i++) {
                                pattern = pattern + "#";
                            }
                            pattern = pattern + "E0";

                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                            formatter.setRoundingMode(roundingMode);
                            formatter.setMinimumFractionDigits(0);
                            String format = formatter.format(number);

                            while (format.length() > numberOfCharacters) {
                                pattern = pattern.replace(".#", ".");
                                pattern = pattern.replace(".E", "E");
                                formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                                formatter.setRoundingMode(roundingMode);
                                formatter.setMinimumFractionDigits(0);
                                format = formatter.format(number);
                            }

                            return format;
                        } else {
                            String pattern = "0.";
                            for (int i = 0; i < numberOfCharacters - 3; i++) {
                                pattern = pattern + "#";
                            }

                            DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                            formatter.setRoundingMode(roundingMode);
                            formatter.setMinimumFractionDigits(0);
                            String format = formatter.format(number);
                            return format;
                        }
                    }
                }
                return "";
            }
        }
//        return "";
    }

    public static String format(BigInteger value, int numberOfCharacters, RoundingMode roundingMode) {
        return BigFormatter.format(new BigDecimal(value), numberOfCharacters, roundingMode);
    }

    private BigFormatter() {
    }
}
