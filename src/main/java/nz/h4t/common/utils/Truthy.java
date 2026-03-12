package nz.h4t.common.utils;

public class Truthy {
    public static boolean toTruthy(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Boolean) {
                result = ((Boolean) obj).booleanValue();
            } else if (obj instanceof Number) {
                result = ((Number) obj).doubleValue() != 0.0;
            } else if (obj instanceof String) {
                result = ((String) obj).equalsIgnoreCase("true") || ((String) obj).equalsIgnoreCase("t")
                        || ((String) obj).equalsIgnoreCase("yes") || ((String) obj).equalsIgnoreCase("y");
            } else {
                result = true;
            }
        }
        return result;
    }
}
