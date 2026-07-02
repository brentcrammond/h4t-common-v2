package nz.h4t.common.web;

import jakarta.ws.rs.core.StreamingOutput;

import java.io.ByteArrayInputStream;

public class StreamingOutputUtils {
    public static StreamingOutput streamOutput(byte[] ba) {
        return os -> {
            try (var is = new ByteArrayInputStream(ba)) {
                var buffer = new byte[4096];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.flush();
            }
        };
    }
}
