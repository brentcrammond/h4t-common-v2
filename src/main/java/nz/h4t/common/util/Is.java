package nz.h4t.common.util;

import java.util.Arrays;

/**
 * The Is class provides utility methods for performing inclusion checks on strings
 * and enumerations. It simplifies conditional checks by allowing verification
 * of membership within a set of provided options.
 */
public class Is {
    /**
     * Checks if the given string is present in the provided list of options.
     *
     * @param s       the string to check for inclusion
     * @param options the list of strings to search within
     * @return true if the string is found in the options, otherwise false
     */
    public static boolean any(String s, String... options) {
        return Arrays.asList(options).contains(s);
    }

    /**
     * Checks if the given string is not present in the provided list of options.
     *
     * @param s       the string to check for non-inclusion
     * @param options the list of strings to search within
     * @return true if the string is not found in the options, otherwise false
     */
    public static boolean notAny(String s, String... options) {
        return !Arrays.asList(options).contains(s);
    }

    /**
     * Determines whether the specified enumeration value is present in the provided list of enumeration options.
     *
     * @param <T>     the type of the enumeration, constrained to types extending {@link Enum}
     * @param en      the enumeration value to check
     * @param options the list of enumeration values to search within
     * @return true if the specified enumeration value is found in the list of options, otherwise false
     */
    public static <T extends Enum<T>> boolean any(Enum<T> en, Enum<T>... options) {
        return Arrays.asList(options).contains(en);
    }

    /**
     * Determines whether the specified enumeration value is not present in the provided list of enumeration options.
     *
     * @param <T>     the type of the enumeration, constrained to types extending {@link Enum}
     * @param en      the enumeration value to check
     * @param options the list of enumeration values to compare against
     * @return true if the specified enumeration value is not found in the list of options, otherwise false
     */
    public static <T extends Enum<T>> boolean notAny(Enum<T> en, Enum<T>... options) {
        return !Arrays.asList(options).contains(en);
    }
}
