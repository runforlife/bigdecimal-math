package io.github.runforlife.bigdecimal.math;

public class IntLongUtils {
    public static int numberOfDigits(int value) {
        if (value == 0) {
            return 1;
        }
        return (int) (Math.log10(Math.abs(value)) + 1);
    }

    public static int numberOfDigits(long value) {
        if (value == 0) {
            return 1;
        }
        return (int) (Math.log10(Math.abs(value)) + 1);
    }

    private IntLongUtils() {
    }
}
