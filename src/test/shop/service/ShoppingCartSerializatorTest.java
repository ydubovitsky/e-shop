package shop.service;

import shop.exception.ValidationException;
import shop.entity.ShoppingCart;

class ShoppingCartSerializatorTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    //TODO Привести в порядок тесты
    @org.junit.jupiter.api.Test
    void shoppingCartToString() {
        try {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.addProduct(123, 4);
            shoppingCart.addProduct(456, 2);
            shoppingCart.addProduct(23, 9);
            String stringCart = new ShoppingCartSerializator().shoppingCartToString(shoppingCart);
            System.out.println(stringCart);
            ShoppingCart shoppingCart1 = new ShoppingCartSerializator().shoppingCartFromString(stringCart);
            System.out.println(shoppingCart1.getTotalCount());
        } catch (ValidationException v) {
            v.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void shoppingCartFromString() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }
}