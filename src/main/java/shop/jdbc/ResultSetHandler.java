package shop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Определяет правила преобразования ResultSet в объект типа T
 * @param <T>
 */
public interface ResultSetHandler<T> {

    T handle(ResultSet resultSet) throws SQLException;

}
