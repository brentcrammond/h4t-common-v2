package nz.h4t.common.util.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface defines a mechanism for processing rows in a {@link ResultSet}.
 * Implementations of this interface can handle individual rows of a ResultSet,
 * allowing custom logic to be applied to each row during iteration.
 * <p>
 * Primarily used in frameworks or utilities to handle query results without
 * materializing the entire ResultSet into memory.
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
