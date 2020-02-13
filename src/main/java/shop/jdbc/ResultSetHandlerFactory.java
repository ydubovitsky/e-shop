package shop.jdbc;

import shop.entity.Category;
import shop.entity.Producer;
import shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory {

    public final static ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER = new ResultSetHandler<Product>() {

        @Override
        public Product handle(ResultSet resultSet) throws SQLException {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setCategory(resultSet.getString("category"));
            product.setDescription(resultSet.getString("description"));
            product.setImageLink(resultSet.getString("image_link"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setProducer(resultSet.getString("producer"));
            return product;
        }
    };

    public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {

        public Category handle(ResultSet resultSet) throws SQLException {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            category.setUrl(resultSet.getString("url"));
            category.setProduct_count(resultSet.getInt("product_count"));
            return category;
        }
    };

    public final static ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER = new ResultSetHandler<Producer>() {
        @Override
        public Producer handle(ResultSet resultSet) throws SQLException {
            Producer producer = new Producer();
            producer.setId(resultSet.getInt("id"));
            producer.setName(resultSet.getString("name"));
            producer.setProductCount(resultSet.getInt("product_count"));
            return producer;
        }
    };

    public final static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return oneRowResultSetHandler.handle(resultSet);
                } else {
                    return null;
                }
            }
        };
    }

    /**
     * Таким образом в метод передается интерфейс, в котором есть метод обработки ResultSet,
     * а этот метод преобразует этот результат как какому то виду
     * @param oneRowResultSetHandler - это интерфейс-надстройка над ResultSet
     * @param <T>
     * @return
     */
    //TODO Как работает этот метод?
    public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            //! ResultSet resultSet - вот это результат запроса из БД
            public List<T> handle(ResultSet resultSet) throws SQLException {
                List<T> list = new ArrayList<>();
                while (resultSet.next()) {
                    //TODO Нужно как то разграничить для большей прозрачности
                    //! ВНИМАНИЕ! Тут oneRowResultSetHandler.handle(resultSet) - это правило преобразования
                    list.add(oneRowResultSetHandler.handle(resultSet));
                }
                return list;
            }
        };
    }
}
