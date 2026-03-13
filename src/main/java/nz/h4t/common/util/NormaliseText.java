package nz.h4t.common.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Utility class for normalizing text by removing diacritical marks (e.g., accents)
 * and converting to pure ASCII characters.
 * <p>
 * This class provides a method to transform strings with accented or special
 * characters into plain ASCII equivalents by using Unicode normalization and
 * removing combining diacritical marks.
 * <p>
 * String subjectString = "öäü";
 * subjectString = Normalizer.normalize(subjectString, Normalizer.Form.NFD);
 * String resultString = subjectString.replaceAll("[^\\x00-\\x7F]", "");
 * <p>
 * => will produce "oau"
 */
public class NormaliseText {
    public static String normalizeASCII(final String string) {
        final String normalize = Normalizer.normalize(string, Normalizer.Form.NFD);
        return Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
                .matcher(normalize)
                .replaceAll("");
    }
}
