package nz.h4t.common.utils;

import java.util.Arrays;

public class Is {
    public static boolean any(String s, String... options) {
        return Arrays.asList(options).contains(s);
    }

    public static boolean notAny(String s, String... options) {
        return !Arrays.asList(options).contains(s);
    }

    public static <T extends Enum<T>> boolean any(Enum<T> en, Enum<T>... options) {
        return Arrays.asList(options).contains(en);
    }

    public static <T extends Enum<T>> boolean notAny(Enum<T> en, Enum<T>... options) {
        return !Arrays.asList(options).contains(en);
    }
}
