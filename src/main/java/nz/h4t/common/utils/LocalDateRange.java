package nz.h4t.common.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class LocalDateRange {
    public static Stream<LocalDate> stream(LocalDate fromDate, LocalDate toDate) {
        if (toDate.isBefore(fromDate)) {
            return Stream.empty();
        }
        return Stream.iterate(fromDate, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(fromDate, toDate) + 1);
    }

    public static Stream<LocalDate> stream(LocalDate fromDate, int noOfDays) {
        LocalDate toDate = fromDate.plusDays(noOfDays);
        if (toDate.isBefore(fromDate)) {
            return Stream.empty();
        }
        return Stream.iterate(fromDate, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(fromDate, toDate));
    }
}
