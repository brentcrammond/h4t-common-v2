package nz.h4t.common.util.info;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * GeneralUtils defines generic utility methods for common operations.
 * This interface provides a way to convert byte counts into human-readable
 * string representations.
 */
public interface GeneralUtils {
    /**
     * Converts a byte count into a human-readable string representation using
     * binary prefixes (e.g., KB, MB, GB, etc.).
     *
     * @param bytes the number of bytes to convert; can be negative to indicate a
     *              negative byte count
     * @return a human-readable string representation of the byte count, such as
     * "1.0KB", "500MB", or "1.2GB"
     */
    default String humanReadableByteCount(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + "B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f%cB", value / 1024.0, ci.current());
    }
}
