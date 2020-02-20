package net.shop.jdbc;

import net.shop.entity.impl.Category;
import net.shop.entity.impl.Producer;
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

    public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {
        @Override
        public Category handler(ResultSet rs) throws SQLException {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setUrl(rs.getString("url"));
            category.setProductCount(rs.getInt("product_count"));
            return category;
        }
    };

    public final static ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER = new ResultSetHandler<Producer>() {
        @Override
        public Producer handler(ResultSet rs) throws SQLException {
            Producer producer = new Producer();
            producer.setId(rs.getInt("id"));
            producer.setCount(rs.getInt("product_count"));
            producer.setName(rs.getString("name"));
            return producer;
        }
    };

    //? Преобразовывает ResultSet в количество продуктов(int count). Не уверен что нужно так писать
    public final static ResultSetHandler<Integer> COUNT_PRODUCT_RESULT_SET_HANDLER = new ResultSetHandler<Integer>() {
        @Override
        public Integer handler(ResultSet rs) throws SQLException {
            return rs.getInt("count");
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

    /**
     * Возвращает список объектов типа Т
     * @param oneRowResultSetHandler
     * @param <T>
     * @return
     */
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
