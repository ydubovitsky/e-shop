package net.shop.service;

import net.shop.form.ProductForm;
import net.shop.model.CurrentAccount;
import net.shop.model.ShoppingCart;
import net.shop.model.SocialAccount;

import javax.servlet.http.HttpServletResponse;

public interface OrderService {

    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

    ShoppingCart deserializeShoppingCart(String cookieValue);

    String serializeShoppingCart(ShoppingCart shoppingCart);

    void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart);

    CurrentAccount authenticate(SocialAccount socialAccount);
}
