package nz.h4t.common.utils;

public class URLJoiner {
    /**
     * This methods will join chunks of URL together with a single '/' between each bit.
     *
     * @param bits
     * @return
     */
    public static String join(String... bits) {
        if (bits == null || bits.length == 0) {
            return null;
        }
        var first = true;
        var sb = new StringBuilder();
        for (var bit : bits) {
            var s = bit;
            if (s != null && !"".equals(s)) {
                if (!first) {
                    if (s.length() > 0) {
                        if (s.charAt(0) == '/') {
                            s = s.substring(1);
                        }
                    }
                    if (sb.length() > 0) {
                        if (sb.charAt(sb.length() - 1) != '/') {
                            sb.append('/');
                        }
                    }
                }
                sb.append(s);
                first = false;
            }
        }
        return sb.toString();
    }
}
