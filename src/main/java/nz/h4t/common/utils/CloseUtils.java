package nz.h4t.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

public class CloseUtils {
    private static final Logger log = LoggerFactory.getLogger(CloseUtils.class);

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.error("Error closing {}", closeable.getClass().getName(), e);
        }
    }

    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.error("Error closing {}", closeable.getClass().getName(), e);
        }
    }
}
