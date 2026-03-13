package nz.h4t.common.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Provides utility methods for working with JDBC {@link ResultSet} objects.
 * This class facilitates the conversion of {@link ResultSet} into a {@link Stream} for
 * streamlined processing of results in a functional programming style.
 */
public class JDBCUtils {
    public static Stream<ResultSet> toStream(ResultSet rs) {
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<ResultSet>(Long.MAX_VALUE, Spliterator.ORDERED) {
            @Override
            public boolean tryAdvance(Consumer<? super ResultSet> action) {
                try {
                    if (rs.next()) {
                        action.accept(rs);
                        return true;
                    }
                    return false;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }, false).onClose(() -> {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
