package nz.h4t.common.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for writing rows of data to an output stream in a delimited format,
 * where pipe ('|') is used as the separator. This class performs automatic escaping
 * of pipe characters within the data to ensure data integrity.
 * <p>
 * The format of the output rows includes:
 * - Fields are separated by a pipe ('|') character.
 * - The pipe character within field values is escaped with a backslash ('\').
 * - Rows are terminated by a carriage return and newline ("\r\n").
 * <p>
 * This class is primarily intended for use cases where data needs to be written in a format
 * suitable for streaming or easy parsing by downstream systems.
 */
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
