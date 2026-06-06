package nz.h4t.common.startup;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.regex.Pattern;

public class StartupFilters {
    public static Function<String, String> translateRedisURL = (redisHosts) -> {
        var pat = Pattern.compile("^(redis://)(\\:[^@]+\\@)?(.+)$");
        var m = pat.matcher(redisHosts);
        if (m.matches()) {
            var g1 = m.group(1);
            var g2 = m.group(2);
            var g3 = m.group(3);
            g2 = StringUtils.isNotEmpty(g2) ? ":%s@".formatted(StringUtils.repeat('*', g2.length() - 2)) : "";
            redisHosts = "%s%s%s".formatted(g1, g2, g3);
        }
        return redisHosts;
    };
}
