package nz.h4t.common.util.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Handy little utility to version a set of files. This will make way for a new file of the same name.
 *
 * @author Brent Crammond
 */
public class VersionFile {
    /**
     * This methods moves through a set of versions.
     *
     * @param file     - File to version
     * @param versions - Number of version to retain.
     * @throws IOException If anything goes wrong when renumber the versions then an IOException will be thrown.
     */
    public static void versionFile(File file, int versions) throws IOException {
        if (file != null) {
            Path filePath = file.toPath();
            Path parentPath = filePath.getParent();
            String fileName = filePath.getFileName().toString();

            Path srcPath;
            Path bkpPath;

            for (var i = versions; i > 1; i--) {
                srcPath = parentPath.resolve(fileName + "." + (i - 1));
                bkpPath = parentPath.resolve(fileName + "." + i);
                if (Files.exists(srcPath)) {
                    Files.move(srcPath, bkpPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            if (Files.exists(filePath)) {
                bkpPath = parentPath.resolve(fileName + ".1");
                Files.move(filePath, bkpPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
