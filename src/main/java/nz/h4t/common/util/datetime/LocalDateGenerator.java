package nz.h4t.common.util.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

/**
 * The LocalDateGenerator class provides utility methods for generating streams of LocalDate objects.
 */
public class LocalDateGenerator {
    /**
     * Generates a sequential stream of {@link LocalDate} objects, starting from the specified
     * {@code fromDate} (inclusive) and ending at the specified {@code toDate} (inclusive).
     * If {@code toDate} is before {@code fromDate}, an empty stream is returned.
     *
     * @param fromDate the starting date of the stream (inclusive)
     * @param toDate   the ending date of the stream (inclusive)
     * @return a sequential stream of {@code LocalDate} objects between {@code fromDate} and {@code toDate}, or an empty stream if {@code toDate} is before {@code fromDate}
     */
    public static Stream<LocalDate> stream(LocalDate fromDate, LocalDate toDate) {
        if (toDate.isBefore(fromDate)) {
            return Stream.empty();
        }
        return Stream.iterate(fromDate, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(fromDate, toDate) + 1);
    }

    /**
     * Generates a sequential stream of {@link LocalDate} objects, starting from the specified
     * {@code fromDate} (inclusive) and extending for the specified number of days.
     * If the calculated end date is before the given start date, an empty stream is returned.
     *
     * @param fromDate the starting date of the stream (inclusive)
     * @param noOfDays the number of days to include in the stream
     * @return a sequential stream of {@code LocalDate} objects starting from {@code fromDate} and spanning {@code noOfDays}, or an empty stream if {@code noOfDays} results in an
     * invalid range
     */
    public static Stream<LocalDate> stream(LocalDate fromDate, int noOfDays) {
        LocalDate toDate = fromDate.plusDays(noOfDays);
        if (toDate.isBefore(fromDate)) {
            return Stream.empty();
        }
        return Stream.iterate(fromDate, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(fromDate, toDate));
    }
}
