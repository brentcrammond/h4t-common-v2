package nz.h4t.common.util;

/**
 * The Truthy class provides utility methods for determining the truthiness of objects.
 * It defines a generalized interpretation of truthy and falsy values based on the type
 * and value of the input object. This can be useful for simplifying conditional expressions.
 */
public class Truthy {
    /**
     * Converts the given object to a truthy boolean value based on its type and content.
     * The interpretation of truthy values is defined as follows:
     * - Boolean objects are evaluated directly by their boolean value.
     * - Number objects are considered truthy when their value is not zero.
     * - String objects are considered truthy when they represent values such as "true",
     * "t", "yes", or "y" (case-insensitive).
     * - Any non-null object that is not a Boolean, Number, or String is considered truthy.
     * - Null objects are always considered falsy.
     *
     * @param obj the input object to evaluate as truthy or falsy
     * @return true if the object is considered truthy, otherwise false
     */
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
