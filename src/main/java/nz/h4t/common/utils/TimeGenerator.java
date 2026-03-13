package nz.h4t.common.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The TimeGenerator class provides static methods to generate a list or a stream of LocalTime objects
 * within a specified range and interval length.
 */
public class TimeGenerator {
    /**
     * Creates a list of LocalTime objects within the specified time range and interval length.
     *
     * @param startTime the starting time (inclusive) of the range
     * @param endTime   the ending time (exclusive) of the range
     * @param len       the length of each interval in minutes
     * @return a list of LocalTime objects representing the intervals within the range
     */
    public static List<LocalTime> createAsList(LocalTime startTime, LocalTime endTime, int len) {
        var times = new ArrayList<LocalTime>();
        var currTime = startTime;
        while (currTime.isBefore(endTime)) {
            times.add(currTime);
            currTime = currTime.plusMinutes(len);
        }
        return times;
    }

    /**
     * Creates a stream of LocalTime objects within the specified time range and interval length.
     *
     * @param startTime the starting time (inclusive) of the range
     * @param endTime   the ending time (exclusive) of the range
     * @param len       the length of each interval in minutes
     * @return a stream of LocalTime objects representing the intervals within the range
     */
    public static Stream<LocalTime> createAsStream(LocalTime startTime, LocalTime endTime, int len) {
        var times = new ArrayList<LocalTime>();
        var currTime = startTime;
        while (currTime.isBefore(endTime)) {
            times.add(currTime);
            currTime = currTime.plusMinutes(len);
        }
        return times.stream();
    }
}