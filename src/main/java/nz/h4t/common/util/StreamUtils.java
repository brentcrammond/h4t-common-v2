package nz.h4t.common.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A utility class providing helper methods for working with streams.
 */
public class StreamUtils {
    /**
     * Returns a predicate that maintains state about previously seen elements and allows filtering
     * of elements based on a distinct key extracted from each element.
     *
     * @param <T>          the type of the input to the predicate
     * @param keyExtractor a function to extract a key from an element for comparison
     * @return a predicate that evaluates to true for elements with distinct keys and false otherwise
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = new HashSet<>();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
