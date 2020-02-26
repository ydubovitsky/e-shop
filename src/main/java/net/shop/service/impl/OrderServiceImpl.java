package net.shop.service.impl;

import net.shop.entity.impl.Product;
import net.shop.exception.InternalServerErrorException;
import net.shop.form.ProductForm;
import net.shop.jdbc.JDBCUtils;
import net.shop.jdbc.ResultSetFactory;
import net.shop.jdbc.ResultSetHandler;
import net.shop.model.ShoppingCart;
import net.shop.service.OrderService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


class OrderServiceImpl implements OrderService {

    private static final ResultSetHandler<Product> productResultSetHandler
            = ResultSetFactory.getSingleResultHandler(ResultSetFactory.PRODUCT_RESULT_SET_HANDLER);

    private DataSource dataSource;



    public OrderServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try(Connection c = dataSource.getConnection()) {
            //* Выполняем запрос к БД и пытаемся найти Product по id
            Product product = JDBCUtils.select(
                    c,
                    "select p.*, c.name as category, pr.name as producer from product p, " +
                    "producer pr, category c where c.id=p.id_category and pr.id=p.id_producer and p.id=?",
                    productResultSetHandler,
                    productForm.getIdProduct());
            if (product == null) { //* если по id не нашли продукт
                throw new InternalServerErrorException("Sorry, we cant find product with id = " + productForm.getIdProduct());
            }
            shoppingCart.addProduct(product, productForm.getCount()); //* если нашли продукт, добавляем в корзину
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }
}
