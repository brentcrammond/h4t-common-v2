package nz.h4t.common.web;

import java.util.List;

public class DistinctColorUtil {
    private static final List<String> colors = List.of("#dcdcdc", "#2f4f4f", "#556b2f", "#8b4513", "#6b8e23", "#a52a2a",
            "#2e8b57", "#191970", "#708090", "#483d8b", "#5f9ea0", "#008000", "#bc8f8f", "#663399", "#bdb76b", "#cd853f", "#4682b4",
            "#d2691e", "#9acd32", "#20b2aa", "#cd5c5c", "#00008b", "#32cd32", "#daa520", "#7f007f", "#8fbc8f", "#b03060", "#66cdaa",
            "#9932cc", "#ff0000", "#ffa500", "#ffd700", "#ffff00", "#c71585", "#0000cd", "#deb887", "#00ff00", "#00fa9a", "#4169e1",
            "#e9967a", "#dc143c", "#00ffff", "#00bfff", "#9370db", "#0000ff", "#a020f0", "#adff2f", "#ff6347", "#d8bfd8", "#ff00ff",
            "#1e90ff", "#db7093", "#f0e68c", "#dda0dd", "#87ceeb", "#ff1493", "#afeeee", "#ee82ee", "#98fb98", "#7fffd4", "#ff69b4",
            "#ffe4c4", "#ffb6c1");

    public static String color(int idx) {
        return colors.get(idx % colors.size());
    }
}
