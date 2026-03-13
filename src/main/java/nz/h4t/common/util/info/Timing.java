package nz.h4t.common.util.info;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

/**
 * The Timing class provides a mechanism for measuring the time taken for a block of code to execute.
 * This class implements the AutoCloseable interface, allowing it to be used in a try-with-resources
 * block. When the block is exited, the time interval is logged, along with location information
 * about where the Timing instance was created.
 * <p>
 * The timing is measured in nanoseconds using {@code System.nanoTime()} and converted to a human-readable
 * format consisting of seconds, milliseconds, and nanoseconds.
 * <p>
 * Location information is inferred from the stack trace to include the file name, method name, and line number
 * where the Timing instance was created. This information is abbreviated or padded to maintain a consistent
 * format in the log output.
 * <p>
 * Logging is performed using SLF4J, and the output includes the location and the duration of the measured interval.
 */
public class Timing implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(Timing.class);
    public final static long MILLI = 1_000_000;
    public final static long SECOND = 1_000 * MILLI;

    private String location = "NA";
    private final long tm;

    public Timing() {
        var stack = StackWalker.getInstance().walk(s -> s.limit(2).collect(Collectors.toList()));
        if (stack.size() == 2) {
            var filename = stack.get(1).getFileName().replace(".java", "");
            var methodName = stack.get(1).getMethodName();
            var lineNo = stack.get(1).getLineNumber();
            location = StringUtils.abbreviate(StringUtils.rightPad(String.format("%s.%s() [%d]", filename, methodName, lineNo), 50, ' '), 50);
        }
        tm = System.nanoTime();
    }

    @Override
    public void close() {
        var took = System.nanoTime() - tm;

        log.info("{}: took {}", location, toString(took).trim());
    }

    //
    // Internal Methods...
    //

    private static String toString(long interval) {
        var sb = new StringBuilder();
        long op, rem;
        rem = interval;

        op = rem / SECOND;
        rem = rem % SECOND;
        if (op > 0) {
            sb.append(String.format("%,ds ", op));
        }

        op = rem / MILLI;
        rem = rem % MILLI;
        if (op > 0) {
            sb.append(String.format("%,dms ", op));
        }

        if (rem > 0) {
            sb.append(String.format("%,dns ", rem));
        }

        return sb.toString().trim();
    }
}
