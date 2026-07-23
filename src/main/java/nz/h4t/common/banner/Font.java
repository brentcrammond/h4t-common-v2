package nz.h4t.common.banner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Yihleego
 */
public class Font {
    public static final Font STANDARD;
    public static final Font BANNER;
    public static final Font BANNER3;
    public static final Font BANNER3D;
    public static final Font BANNER4;

    protected static final List<Font> VALUES;
    protected static final Map<String, Font> MAP;
    protected static final String ROOT_DIR_PATH = "banner";
    protected static final String FONT_DIR_PATH = ROOT_DIR_PATH + "/fonts/";

    static {
        List<Font> values = new ArrayList<>();
        values.add(STANDARD = new Font("Standard", "Standard.flf"));
        values.add(BANNER = new Font("Banner", "Banner.flf"));
        values.add(BANNER3 = new Font("Banner3", "Banner3.flf"));
        values.add(BANNER3D = new Font("Banner3D", "Banner3-D.flf"));
        values.add(BANNER4 = new Font("Banner4", "Banner4.flf"));
        Map<String, Font> map = new HashMap<>(values.size());
        for (Font v : values) {
            map.put(v.name, v);
        }
        VALUES = Collections.unmodifiableList(values);
        MAP = Collections.unmodifiableMap(map);
    }

    protected final String name;
    protected final String filename;
    protected final Charset charset;

    protected Font(String name) {
        this.name = name;
        this.filename = null;
        this.charset = StandardCharsets.UTF_8;
    }

    protected Font(String name, String filename) {
        this.name = name;
        this.filename = filename;
        this.charset = StandardCharsets.UTF_8;
    }

    protected Font(String name, String filename, Charset charset) {
        this.name = name;
        this.filename = filename;
        this.charset = charset;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public Charset getCharset() {
        return charset;
    }

    public InputStream getInputStream() throws IOException {
        InputStream inputStream = Font.class.getClassLoader().getResourceAsStream(FONT_DIR_PATH + filename);
        if (inputStream == null) {
            throw new RuntimeException("Failed to load font '" + this.name + "', the specified font does not exist.");
        }
        return convertIfZipped(inputStream);
    }

    /**
     * Returns a {@link ZipInputStream} if the input stream can be converted.
     *
     * @param inputStream the input stream.
     * @return a {@link ZipInputStream} if the input stream can be converted.
     * @throws IOException if an exception occurs during converting.
     */
    protected static InputStream convertIfZipped(InputStream inputStream) throws IOException {
        // Detects zipped font.
        BufferedInputStream bufferedInputStream = inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
        if (isZipped(bufferedInputStream)) {
            // Expects a single anonymous entry.
            ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
            ZipEntry entry = zipInputStream.getNextEntry();
            if (entry == null) {
                throw new RuntimeException("Failed to convert the InputStream.");
            }
            return zipInputStream;
        } else {
            return bufferedInputStream;
        }
    }

    /**
     * Returns {@code true} if the buffered input stream start with {@code 0x504b0304}.
     *
     * @param bufferedInputStream the buffered input stream.
     * @return {@code true} if the buffered input stream start with {@code 0x504b0304}.
     * @throws IOException if an exception occurs during detecting.
     */
    protected static boolean isZipped(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] buf = new byte[4];
        bufferedInputStream.mark(4);
        bufferedInputStream.read(buf);
        bufferedInputStream.reset();
        return Arrays.equals(buf, new byte[]{0x50, 0x4b, 0x03, 0x04});
    }

    /**
     * Returns all fonts.
     *
     * @return all fonts.
     */
    public static List<Font> values() {
        return VALUES;
    }

    /**
     * Returns the font with the specified name, or {@code null} if the font does not exist.
     *
     * @param name the font name.
     * @return the font with the specified name, or {@code null} if the font does not exist.
     */
    public static Font get(String name) {
        return MAP.get(name);
    }

    /**
     * Returns the font with the specified name, or {@code defaultValue} if the font does not exist.
     *
     * @param name         the font name.
     * @param defaultValue the default font.
     * @return the font with the specified name, or {@code defaultValue} if the font does not exist.
     */
    public static Font getOrDefault(String name, Font defaultValue) {
        Font font;
        return (font = MAP.get(name)) != null ? font : defaultValue;
    }

}
