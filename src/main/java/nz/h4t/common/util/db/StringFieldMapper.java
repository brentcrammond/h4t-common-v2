package nz.h4t.common.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A {@link RowMapper} implementation for extracting a specific string field from a {@link ResultSet}.
 * The name of the target column is provided at the time of instantiation, and this class maps
 * each row of the {@link ResultSet} to the value of the specified column.
 * <p>
 * This implementation is particularly useful for simplifying the extraction of single-column
 * string fields from query results.
 */
public class StringFieldMapper implements RowMapper<String> {
    private final String fieldname;

    /**
     * Constructs a new {@code StringFieldMapper} with the specified field name.
     * The field name identifies the column in the {@link ResultSet} whose value will be extracted
     * during row mapping.
     *
     * @param fieldname the name of the column in the {@link ResultSet} to map; must not be null.
     */
    public StringFieldMapper(String fieldname) {
        this.fieldname = fieldname;
    }

    /**
     * Maps a single row of the given {@link ResultSet} to a {@link String} by extracting
     * the value of the specified column.
     *
     * @param rs     the {@link ResultSet} representing the database results; must not be null.
     * @param rowNum the number of the current row in the {@link ResultSet}, starting from 0.
     * @return the {@link String} value of the specified column in the current row.
     * @throws SQLException if an SQL exception occurs while accessing the {@link ResultSet}.
     */
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString(fieldname);
    }
}
