package nz.h4t.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * The ResourceUtil class provides utility methods for working with application resources.
 * It simplifies operations like reading the contents of resource files as strings.
 */
public class ResourceUtil {
    /**
     * Reads the content of a resource file from the classpath and returns it as a single string.
     *
     * @param fileName the name of the resource file to read, relative to the classpath
     * @return the content of the resource file as a string, or null if the file cannot be found
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static String getResourceFileAsString(String fileName) throws IOException {
        var classLoader = ResourceUtil.class.getClassLoader();
        try (var is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) {
                return null;
            }
            try (var isr = new InputStreamReader(is);
                 var reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
