package shop.model;

import shop.entity.Product;

public class ShoppingCartSerializator {

    public String shoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : shoppingCart.getItems()) {
            stringBuilder.append("i:" + product.getIdProduct() + "c:" + product.getCount() + ";");
        }
        return stringBuilder.toString();
    }

    public ShoppingCart shoppingCartFromString(String stringShoppingCart) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] split = stringShoppingCart.split(";");
        for (int i = 0; i < split.length; i++) {
            String id = split[i].substring(2, split[i].lastIndexOf("c:"));
            System.out.println(id);
            System.out.println(split[i].substring(id.length() + 4));
        }
        return shoppingCart;
    }

    public static void main(String[] args) {
        ShoppingCartSerializator shoppingCartSerializator = new ShoppingCartSerializator();
        shoppingCartSerializator.shoppingCartFromString("i:123346473473445c:09876;");
    }

}
