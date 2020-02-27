package net.shop.service.impl;

import net.shop.entity.impl.Product;
import net.shop.exception.InternalServerErrorException;
import net.shop.form.ProductForm;
import net.shop.jdbc.JDBCUtils;
import net.shop.jdbc.ResultSetFactory;
import net.shop.jdbc.ResultSetHandler;
import net.shop.model.ShoppingCart;
import net.shop.model.ShoppingCartItem;
import net.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final ResultSetHandler<Product> productResultSetHandler
            = ResultSetFactory.getSingleResultHandler(ResultSetFactory.PRODUCT_RESULT_SET_HANDLER);

    private DataSource dataSource;

    /**
     * Constructor
     * @param dataSource
     */
    public OrderServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    /**
     * Добавить продукт в корзину
     */
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

    /**
     * Удалить продукт из корзины
     */
    @Override
    public void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(form.getIdProduct(), form.getCount());
    }

    /**
     * Получить строку из Cookies
     */
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            res.append(shoppingCartItem.getProduct().getId()).append("-").append(shoppingCartItem.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    /**
     * Восстановление корзины их cookieValue
     * @param cookieValue
     * @return
     */
    public ShoppingCart deserializeShoppingCart(String cookieValue) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = cookieValue.split("\\|");
        for (String item : items) {
            try {
                String data[] = item.split("-");
                int idProduct = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                addProductToShoppingCart(new ProductForm(idProduct, count), shoppingCart);
            } catch (RuntimeException e) {
                LOGGER.error("Cant add product to Shopping Cart with help deserializeShoppingCart item =  " + item, e);
            }
        }
        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
    }
}
