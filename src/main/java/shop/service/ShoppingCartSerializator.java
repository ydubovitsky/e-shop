package shop.service;

import shop.entity.Product;
import shop.exception.ValidationException;
import shop.entity.ShoppingCart;

/**
 * This class is designed to serialize and deserialize the ShoppingCart object.
 */
public class ShoppingCartSerializator {

    /**
     * This method returns a string representation of ShoppingCart in the format "idProduct:count;"
     * @param shoppingCart
     * @return
     */
    public String shoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : shoppingCart.getItems()) {
            stringBuilder.append(product.getIdProduct() + ":" + product.getCount() + "|");
        }
        return stringBuilder.toString();
    }

    /**
     * This method returns an object of ShoppingCart from the String representation "idProduct:count;"
     * @param stringShoppingCart
     * @return
     */
    public ShoppingCart shoppingCartFromString(String stringShoppingCart) {
        ShoppingCart shoppingCart = new ShoppingCart();
        //TODO Нужно избавиться от констант, вынести их за пределы метода и сделать переменными
        String[] split = stringShoppingCart.split("|");
        for (int i = 0; i < split.length; i++) {
            String id = split[i].substring(0, split[i].lastIndexOf(":"));
            String count = split[i].substring(id.length() + 1);
            try {
                shoppingCart.addProduct(Integer.parseInt(id), Integer.parseInt(count));
            } catch (ValidationException v) {
                v.printStackTrace();
            }
        }
        return shoppingCart;
    }
}
