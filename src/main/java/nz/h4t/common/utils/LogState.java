package nz.h4t.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
