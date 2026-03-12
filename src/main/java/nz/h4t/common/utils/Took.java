package nz.h4t.common.utils;

/**
 * This class was forked from a version developed by Anameg Consulting.
 * Copyright Anameg Consulting 2006-2019, All Rights Reserved
 */
public class Took {
    private final static String FMT = "wdhms";
    private final static long SECOND = 1000;
    private final static long MINUTE = 60 * SECOND;
    private final static long HOUR = 60 * MINUTE;
    private final static long DAY = 24 * HOUR;
    private final static long WEEK = 7 * DAY;

    private long startTim;

    public static Took start() {
        return new Took();
    }

    private Took() {
        startTim = System.currentTimeMillis();
    }

    public String took() {
        return toString(System.currentTimeMillis() - startTim, FMT);
    }

    public String took(String fmt) {
        return toString(System.currentTimeMillis() - startTim, fmt);
    }

    //
    // Internal Methods...
    //

    private String toString(long interval, String fmt) {
        fmt = O.coalesce(fmt, FMT).toLowerCase();
        var tStr = "";
        var lastUnit = "";
        long op, rem;

        rem = interval;
        if (fmt.indexOf('w') >= 0) {
            op = rem / WEEK;
            rem = rem % WEEK;
            lastUnit = getDesc('W', fmt, op, "week");
            if (op > 0) {
                tStr += lastUnit;
            }
        }
        if (fmt.indexOf('d') >= 0) {
            op = rem / DAY;
            rem = rem % DAY;
            lastUnit = getDesc('D', fmt, op, "day");
            if (op > 0) {
                tStr += lastUnit;
            }
        }
        if (fmt.indexOf('h') >= 0) {
            op = rem / HOUR;
            rem = rem % HOUR;
            lastUnit = getDesc('H', fmt, op, "hour");
            if (op > 0) {
                tStr += lastUnit;
            }
        }
        if (fmt.indexOf('m') >= 0) {
            op = rem / MINUTE;
            rem = rem % MINUTE;
            lastUnit = getDesc('M', fmt, op, "minute");
            if (op > 0) {
                tStr += lastUnit;
            }
        }
        if (fmt.indexOf('s') >= 0) {
            op = rem / SECOND;
            rem = rem % SECOND;
            lastUnit = getDesc('S', fmt, op, "second");
            if (op > 0) {
                tStr += lastUnit;
            }
        }
        if (fmt.indexOf('l') >= 0) {
            if (rem > 0) {
                lastUnit = String.valueOf(rem);
            }
            if (rem > 0) {
                tStr += lastUnit;
            }
        }
        if (tStr.length() == 0) {
            tStr = lastUnit;
        }
        return tStr;
    }

    private static String getDesc(char match, String fmt, long count, String singular) {
        if (fmt != null && fmt.indexOf(match) >= 0) {
            return count + " " + singular + (count != 1 ? "s " : " ");
        }
        return String.valueOf(count) + Character.toLowerCase(match) + " ";
    }
}
