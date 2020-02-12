package shop.jdbc;

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
            product.setName(resultSet.getString("name"));
            product.setCategory(resultSet.getString("category"));
            product.setDescription(resultSet.getString("description"));
            product.setImageLink(resultSet.getString("image_link"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setProducer(resultSet.getString("producer"));
            return product;
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

    public final static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet resultSet) throws SQLException {
                List<T> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(oneRowResultSetHandler.handle(resultSet));
                }
                return list;
            }
        };
    }


}
