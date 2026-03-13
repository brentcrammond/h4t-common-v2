package nz.h4t.common.util.db;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Utility interface for converting SQL date/time objects into their corresponding
 * Java 8+ {@code java.time} representations.
 * This interface provides default methods to transform SQL types such as {@link Date},
 * {@link Time}, and {@link Timestamp} into {@link LocalDate}, {@link LocalTime},
 * and {@link LocalDateTime}, respectively.
 * These methods handle null inputs gracefully, returning null if the provided value is null.
 */
public interface RowMapperUtils {
    /**
     * Converts a {@link Date} object to a {@link LocalDate} instance.
     * If the input {@link Date} object is {@code null}, this method returns {@code null}.
     *
     * @param dt the {@link Date} object to be converted; can be {@code null}.
     * @return the corresponding {@link LocalDate} if {@code dt} is not {@code null};
     * otherwise, {@code null}.
     */
    default LocalDate convert(Date dt) {
        if (dt != null) {
            return dt.toLocalDate();
        } else {
            return null;
        }
    }

    /**
     * Converts a {@link Time} object to a {@link LocalTime} instance.
     * If the input {@link Time} object is {@code null}, this method returns {@code null}.
     *
     * @param tim the {@link Time} object to be converted; can be {@code null}.
     * @return the corresponding {@link LocalTime} if {@code tim} is not {@code null};
     * otherwise, {@code null}.
     */
    default LocalTime convert(Time tim) {
        if (tim != null) {
            return tim.toLocalTime();
        } else {
            return null;
        }
    }

    /**
     * Converts a {@link Timestamp} object to a {@link LocalDateTime} instance.
     * If the input {@link Timestamp} object is {@code null}, this method returns {@code null}.
     *
     * @param tsp the {@link Timestamp} object to be converted; can be {@code null}.
     * @return the corresponding {@link LocalDateTime} if {@code tsp} is not {@code null};
     * otherwise, {@code null}.
     */
    default LocalDateTime convert(Timestamp tsp) {
        if (tsp != null) {
            return tsp.toLocalDateTime();
        } else {
            return null;
        }
    }
}
