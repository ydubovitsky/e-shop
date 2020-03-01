package net.shop.service.impl;

import net.shop.entity.impl.Account;
import net.shop.entity.impl.Order;
import net.shop.entity.impl.Product;
import net.shop.exception.InternalServerErrorException;
import net.shop.form.ProductForm;
import net.shop.jdbc.JDBCUtils;
import net.shop.jdbc.ResultSetFactory;
import net.shop.jdbc.ResultSetHandler;
import net.shop.model.CurrentAccount;
import net.shop.model.ShoppingCart;
import net.shop.model.ShoppingCartItem;
import net.shop.model.SocialAccount;
import net.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final ResultSetHandler<Product> productResultSetHandler
            = ResultSetFactory.getSingleResultHandler(ResultSetFactory.PRODUCT_RESULT_SET_HANDLER);
    private static final ResultSetHandler<Account> accountResultSetHandler
            = ResultSetFactory.getSingleResultHandler(ResultSetFactory.ACCOUNT_RESULT_SET_HANDLER);
    private static final ResultSetHandler<Order> orderResultSetHandler =
            ResultSetFactory.getSingleResultHandler(ResultSetFactory.ORDER_RESULT_SET_HANDLER);

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

    @Override
    public CurrentAccount authenticate(SocialAccount socialAccount) {
        try(Connection c = dataSource.getConnection()) {
            Account account = JDBCUtils.select(c, "select * from account where email=?", accountResultSetHandler, socialAccount.getEmail());
            if (account == null) {
                account = new Account(socialAccount.getName(), socialAccount.getEmail());
                account = JDBCUtils.insert(c, "insert into account values (nextval('account_seq'), ?, ?)", accountResultSetHandler, account.getName(), account.getEmail());
                c.commit();
            }
            return account;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }

    /**
     * Сделать заказ
     *
     * @param shoppingCart   - принимает корзину пользователя
     * @param currentAccount - текущего пользователя из сессии
     * @return
     */
    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            throw new InternalServerErrorException("shopping cart is null or empty");
        }
        try(Connection c = dataSource.getConnection()){
            Order order = JDBCUtils.insert(c, "insert into \"order\" values(nextval('order_seq'), ? , ?)", orderResultSetHandler,
                    currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
            JDBCUtils.insertBatch(c, "insert into order_item values(nextval('order_item_seq'), ?, ?, ?)",
                    toOrderItemParameterList(order.getId(), shoppingCart.getItems())); //! Для того чтобы заполнить таблицу order_item нужно: idOrder, item.getProduct().getId(), item.getCount()
            c.commit();
            return order.getId(); //?
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query: " + e.getMessage(), e);
        }
    }


    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items) {
        List<Object[]> parameterList = new ArrayList<>();
        for (ShoppingCartItem item : items) {
            parameterList.add(new Object[]{idOrder, item.getProduct().getId(), item.getCount()});
        }
        return parameterList;
    }
}
