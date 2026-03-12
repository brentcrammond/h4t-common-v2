package nz.h4t.common.utils;

/**
 * This class was forked from a version developed by Anameg Consulting.
 * Copyright Anameg Consulting 2006-2019, All Rights Reserved
 */
public class O {

    public static <T> T coalesce(T... items) {
        for (var i : items) {
            if (i != null) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    public static <T> T coalesce(T a, T b) {
        return a == null ? checkNotNull(b) : a;
    }

    public static <T> T coalesce(T a, T b, T c) {
        return a != null ? a : (b != null ? b : checkNotNull(c));
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

}
