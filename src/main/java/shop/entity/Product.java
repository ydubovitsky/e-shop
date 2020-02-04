package shop.entity;

import java.util.Objects;

/**
 * Product class
 */
public class Product {

    private int idProduct;
    private int count;

    public Product(int idProduct, int count) {
        this.idProduct = idProduct;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getIdProduct() == product.getIdProduct() &&
                getCount() == product.getCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduct(), getCount());
    }
}
