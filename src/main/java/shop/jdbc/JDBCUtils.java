package shop.jdbc;

import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class JDBCUtils {

    /**
     *
     * @param c
     * @param sql
     * @param resultSetHandler - определяет правила преобразования ResultSet в объект
     * @param parameters
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> T select(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... parameters) throws SQLException {
        PreparedStatement statement = c.prepareStatement(sql);
        populatePreparedStatement(statement, parameters);
        ResultSet resultSet = statement.executeQuery();
        return resultSetHandler.handle(resultSet);
    }

    public static void populatePreparedStatement(PreparedStatement statement, Object... objects) throws SQLException {
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
        }
    }

    private JDBCUtils() {
    }
}
