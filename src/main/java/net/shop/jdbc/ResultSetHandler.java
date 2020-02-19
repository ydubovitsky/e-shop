package net.shop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс отвечает за преобразование ResultSet в нужный объект Т.
 * @param <T>
 */
public interface ResultSetHandler<T> {

    /**
     * Получает Result set и определяет правила преобразования ResultSet в объекты
     * @param rs
     * @return
     * @throws SQLException
     */
    T handler(ResultSet rs) throws SQLException;

}
