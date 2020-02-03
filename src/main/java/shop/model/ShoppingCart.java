package shop.model;

import shop.entity.Product;
import shop.exception.ValidationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Integer, Product> productsMap = new HashMap<>();
    private int maxProductCount = 20;

    public void addProduct(int idProduct, int count) throws ValidationException {
        if (count > maxProductCount || count < 0) {
            throw new ValidationException();
        }
        productsMap.put(idProduct, new Product(idProduct, count));
    }

    public void removeProduct(int idProduct, int count) throws ValidationException {
        Product product = productsMap.get(idProduct);
        if (product.getCount() > count || product.getCount() == count) {
            Integer newCount = product.getCount() - count;
            product.setCount(newCount);
            productsMap.put(idProduct, product);
        } else {
            throw new ValidationException();
        }
    }

    public Collection<Product> getItems() {
        return productsMap.values();
    }

    public int getTotalCount() {
        return productsMap.size();
    }
}
