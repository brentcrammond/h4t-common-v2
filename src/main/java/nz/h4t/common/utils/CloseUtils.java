package nz.h4t.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;

@Slf4j
public class CloseUtils {
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
