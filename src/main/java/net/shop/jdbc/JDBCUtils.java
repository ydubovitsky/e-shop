package net.shop.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCUtils {

    /**
     * Метод получает sql запрос с параметрами. Выполняет запрос, получает ResultSet -> и преобразует ResultSet в объект
     * благодаря методу из интерфейса public interface ResultSetHandler<T>
     * @param c
     * @param sql - PreparedStatement с параметрами ? ? ?
     * @param resultSet
     * @param parameters - параметры ? ? ?
     * @param <T> - тип возвращаемого объекта
     * @return - возвращает ОБЪЕКТ, который получается путем преобразования ResultSet в T
     * @throws SQLException
     */
    public static <T> T select(Connection c, String sql, ResultSetHandler<T> resultSet, Object... parameters) throws SQLException {
        try(PreparedStatement statement = c.prepareStatement(sql)) {
            populatePreparedStatement(statement, parameters);
            ResultSet rs = statement.executeQuery();
            return resultSet.handler(rs); //! Метод получает ResultSet и получает объекты типа T
        }
    }

    /**
     * Заполняет PreparedStatement параметрами ?, ?, ? - Object... parameters
     * @param ps
     * @param params
     * @throws SQLException
     */
    private static void populatePreparedStatement(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]); //? Зачем тут +1?
            }
        }
    }

    public static void populateSqlAndParams(StringBuilder sql, List<Object> params, List<Integer> list, String expression) {
        if (list != null && !list.isEmpty()) {
            sql.append(" and (");
            for (int i = 0; i < list.size(); i++) {
                sql.append(expression);
                params.add(list.get(i));
                if (i != list.size() - 1) {
                    sql.append(" or ");
                }
            }
            sql.append(")");
        }
    }

    public static <T> T insert(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... params) throws SQLException{
        try(PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            populatePreparedStatement(ps, params);
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new SQLException("Cant insert row to the database. Result=" + result);
            }
            ResultSet rs = ps.getGeneratedKeys(); //* Возвращает объект ResultSet
            return resultSetHandler.handler(rs);
        }
    }

    private JDBCUtils() {}
}
