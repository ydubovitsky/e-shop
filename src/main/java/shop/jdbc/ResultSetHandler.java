package shop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Определяет правила преобразования ResultSet в объект типа T
 * @param <T>
 */
public interface ResultSetHandler<T> {

    //! Этот метод получает в качестве параметра ResultSet, обрабатывает его и приводит к виду(типу) T
    T handle(ResultSet resultSet) throws SQLException;

}
