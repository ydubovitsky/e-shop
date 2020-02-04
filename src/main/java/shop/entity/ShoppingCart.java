package shop.entity;

import shop.exception.ValidationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Shopping cart class
 */
public class ShoppingCart {

    /**
     * Map with idProduct and Product
     */
    private Map<Integer, Product> productsMap = new HashMap<>();

    /**
     * Maximum number of products, that a user can add to the shopping cart
     */
    private int maxProductCount = 20;

    /**
     * Default Constructor
     */
    public ShoppingCart() { //! Без этого конструктора, объект не сохранялся в сессию!

    }

    /**
     * Add new product into the Shopping Cart
     * @param idProduct
     * @param count
     * @throws ValidationException
     */
    public void addProduct(int idProduct, int count) throws ValidationException {
        if (count > maxProductCount || count < 0) {
            throw new ValidationException();
        }
        productsMap.put(idProduct, new Product(idProduct, count));
    }

    /**
     * This method allows you to delete an item from the cart, if the 'count' > product parameter is entered.getCount () in the shopping cart.
     * @param idProduct
     * @param count
     * @throws ValidationException
     */
    public void removeProduct(int idProduct, int count) throws ValidationException {
        Product product = productsMap.get(idProduct);
        if (product.getCount() > count) {
            int newCount = product.getCount() - count;
            product.setCount(newCount);
            productsMap.put(idProduct, product); // так как у нас хэш-мап то будет произведена замена элемента с тем же id
        } else {
            productsMap.remove(product);
        }
    }

    public Collection<Product> getItems() {
        return productsMap.values();
    }

    public int getTotalCount() {
        return productsMap.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCart)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return maxProductCount == that.maxProductCount &&
                productsMap.equals(that.productsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productsMap, maxProductCount);
    }
}
