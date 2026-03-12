package nz.h4t.common.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class PipeWriter implements AutoCloseable {
    private static final String SEPARATOR = "|";
    private static final String ESCAPE = "\\";
    private static final String LINE_TERMINATOR = "\r\n";

    private final Writer wrt;

    public PipeWriter(Writer wrt) {
        this.wrt = wrt;
    }

    public void writeNext(List<String> row) throws IOException {
        var ln = row.stream()
                .map(s -> s != null ? s : "")
                .map(s -> s.replace(SEPARATOR, ESCAPE + SEPARATOR))
                .collect(Collectors.joining(SEPARATOR)) + LINE_TERMINATOR;
        wrt.write(ln);
    }

    @Override
    public void close() throws Exception {
        if (wrt != null) {
            wrt.close();
        }
    }
}
