package nz.h4t.common.util.datetime;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Provides utility methods for parsing date strings into {@link LocalDate} objects.
 * This class attempts to parse input strings using various date patterns that support
 * different date formats.
 */
public class LocalDateUtils {
    public static LocalDate parseDates(String sdt) {
        if (StringUtils.isBlank(sdt)) {
            return null;
        }
        LocalDate ldt = null;
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("d-MMM-yyyy"));
        } catch (Exception ignore) {
        }
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (Exception ignore) {
        }
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("yyyy-M-d"));
        } catch (Exception ignore) {
        }

        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("d/MMM/yyyy"));
        } catch (Exception ignore) {
        }
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (Exception ignore) {
        }
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("yyyy/M/d"));
        } catch (Exception ignore) {
        }

        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (Exception ignore) {
        }
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("d M yyyy"));
        } catch (Exception ignore) {
        }
        try {
            return LocalDate.parse(sdt, DateTimeFormatter.ofPattern("yyyy M d"));
        } catch (Exception ignore) {
        }
        return null;
    }
}
