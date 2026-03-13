package nz.h4t.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * Utility class to handle the closing of resources that implement {@link Closeable} or {@link AutoCloseable}.
 * Provides methods to safely close resources without requiring boilerplate try-catch blocks.
 * Any exceptions encountered during resource closure will be logged as errors.
 */
public class CloseUtils {
    private static final Logger log = LoggerFactory.getLogger(CloseUtils.class);

    /**
     * Safely closes a given {@link Closeable} resource, suppressing any exceptions
     * encountered during the close operation. If an exception occurs, it will be logged as an error.
     *
     * @param closeable the {@link Closeable} resource to be closed; if null, this method performs no action
     */
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.error("Error closing {}", closeable.getClass().getName(), e);
        }
    }

    /**
     * Safely closes the provided {@link AutoCloseable} resource, suppressing any exceptions
     * that may occur during the close operation. Any errors encountered will be logged.
     *
     * @param closeable the {@link AutoCloseable} resource to be closed; if null, this method does nothing
     */
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
