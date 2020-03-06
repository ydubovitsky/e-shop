package net.shop.service;

import net.shop.entity.impl.Order;
import net.shop.form.ProductForm;
import net.shop.model.CurrentAccount;
import net.shop.model.ShoppingCart;
import net.shop.model.SocialAccount;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {

    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    ShoppingCart deserializeShoppingCart(String cookieValue);

    String serializeShoppingCart(ShoppingCart shoppingCart);

    void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart);

    CurrentAccount authenticate(SocialAccount socialAccount);

    /**
     * Сделать заказ
     * @param shoppingCart - принимает корзину пользователя
     * @param currentAccount - текущего пользователя из сессии
     * @return
     */
    long makeOrder (ShoppingCart shoppingCart, CurrentAccount currentAccount);

    /**
     * Вернуть Список всех заказов
     * @return
     */
    List<Order> getListOrder();

    Order findOrderById(long id, CurrentAccount currentAccount);

    List<Order> listMyOrders(CurrentAccount account, int page, int limit);

    int countMyOrders(CurrentAccount account);
}
