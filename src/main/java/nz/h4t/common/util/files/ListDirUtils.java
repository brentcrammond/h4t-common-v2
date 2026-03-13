package nz.h4t.common.util.files;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Utility class for directory-related operations.
 */
public class ListDirUtils {
    /**
     * Returns a stream of {@link File} objects representing the contents of the specified directory.
     * If the directory is null or does not contain any files, an empty stream is returned.
     *
     * @param dir the directory from which to stream its contents. Can be null.
     * @return a {@code Stream<File>} representing the files in the specified directory,
     * or an empty stream if the directory is null or contains no files.
     */
    public static Stream<File> stream(File dir) {
        if (dir == null) {
            return Stream.empty();
        }
        var fs = dir.listFiles();
        if (fs == null) {
            return Stream.empty();
        }
        return Arrays.stream(fs);
    }
}
