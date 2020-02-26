package net.shop.service;

import net.shop.form.ProductForm;
import net.shop.model.ShoppingCart;

public interface OrderService {

    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);

}
