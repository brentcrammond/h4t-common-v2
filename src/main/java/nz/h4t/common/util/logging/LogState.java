package nz.h4t.common.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The LogState class provides a utility method to log the current location
 * in the execution stack. This is useful for debugging and tracing program execution.
 * It logs the file name, method name, and line number of the calling code
 * where the logging method is invoked.
 * <p>
 * The log message is formatted in the following structure:
 * "@ {filename}.{methodName}() [{lineNumber}]".
 * <p>
 * The logging is performed using the SLF4J Logger.
 */
public class LogState {
    private final static Logger log = LoggerFactory.getLogger(LogState.class);

    public static void at() {
        var stack = StackWalker.getInstance().walk(s -> s.limit(2).toList());
        if (stack.size() == 2) {
            var filename = stack.get(1).getFileName().replace(".java", "");
            var methodName = stack.get(1).getMethodName();
            var lineNo = stack.get(1).getLineNumber();
            log.info("@ {}.{}() [{}]", filename, methodName, lineNo);
        }
    }
}
