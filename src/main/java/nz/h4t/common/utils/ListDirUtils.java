package nz.h4t.common.utils;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class ListDirUtils {
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
