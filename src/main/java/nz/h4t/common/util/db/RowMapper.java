package nz.h4t.common.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface for mapping rows of a {@link ResultSet} to objects of a specified type.
 * Implementations of this interface define the mapping logic for converting each row
 * of the {@link ResultSet} into the desired object.
 * <p>
 * This is primarily used in scenarios where individual rows of a {@link ResultSet}
 * need to be transformed into domain objects without loading all the data into memory at once.
 *
 * @param <T> the type of object the row should be mapped to
 */
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
