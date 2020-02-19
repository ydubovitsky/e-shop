package net.shop.jdbc;

import net.shop.entity.impl.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetFactory {

    /**
     * Класс, реализующий интерфейс interface ResultSetHandler<Product>,
     * метод в качетсве параметра получает ResultSet и возвращает объект класса, переданного в качестве параметра обобщения
     */
    public final static ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER = new ResultSetHandler<Product>() {
        @Override
        public Product handler(ResultSet rs) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setCategory(rs.getString("category"));
            product.setDescription(rs.getString("description"));
            product.setName(rs.getString("name"));
            product.setImageLink(rs.getString("image_link"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setProducer(rs.getString("producer"));
            return product;
        }
    };

    /**
     * Эти методы возвращают анонимные классы, реализующие интерфейсы interface ResultSetHandler<T>.
     * В качестве параметра передается объект класса ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER,
     * у которого есть метод, преобразующий ResultSet в объект класса T
     *
     * @param oneRowResultSetHandler
     * @param <T>
     * @return
     */
    public final static <T> ResultSetHandler<T> getSingleResultHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<T>() { //! Этот анонимный метод возвращает класс
            @Override
            public T handler(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return oneRowResultSetHandler.handler(rs);
                } else {
                    return null;
                }
            }
        };
    }

    public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handler(ResultSet rs) throws SQLException {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(oneRowResultSetHandler.handler(rs));
                }
                return list;
            }
        };
    }
}
