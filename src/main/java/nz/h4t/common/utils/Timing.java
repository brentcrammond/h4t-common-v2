package nz.h4t.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

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
