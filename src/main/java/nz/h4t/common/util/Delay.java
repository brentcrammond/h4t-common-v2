package nz.h4t.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

/**
 * The Delay class provides utility methods to pause the execution of the current thread
 * for a specified duration. It includes multiple methods to support different representations
 * of time, including milliseconds, a combination of time and temporal unit, or a {@link Duration}.
 * <p>
 * These methods handle {@link InterruptedException} internally by logging the exception
 * and ensuring proper error tracking.
 */
public class Delay {
    private static final Logger log = LoggerFactory.getLogger(Delay.class);

    /**
     * Pauses the execution of the current thread for the specified duration in milliseconds.
     * If the thread is interrupted during sleep, the interruption is caught and logged.
     *
     * @param ms the number of milliseconds the thread should sleep
     */
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Sleep interrupted", e);
        }
    }

    /**
     * Pauses the execution of the current thread for the specified duration defined by a time value and its unit.
     * If the thread is interrupted during the sleep, the interruption is caught and logged.
     *
     * @param tm   the time value specifying the length of the pause
     * @param unit the unit of time to be used for the specified time value
     */
    public static void sleep(long tm, TemporalUnit unit) {
        try {
            Thread.sleep(Duration.of(tm, unit));
        } catch (InterruptedException e) {
            log.error("Sleep interrupted", e);
        }
    }

    /**
     * Pauses the execution of the current thread for the specified duration.
     * The duration is represented by a {@link Duration} object, which provides
     * a time-based abstraction. If the thread is interrupted while sleeping,
     * the interruption is caught and logged.
     *
     * @param dur the duration for which the thread should pause
     */
    public static void sleep(Duration dur) {
        try {
            var ms = dur.toMillis();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("Sleep interrupted", e);
        }
    }
}
