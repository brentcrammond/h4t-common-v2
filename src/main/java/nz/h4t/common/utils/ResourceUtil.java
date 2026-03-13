package nz.h4t.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceUtil {
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
