package shop.entity;

/**
 * Product class
 */
public class Product {

    private int idProduct;
    private String name;
    private int count;

    public Product(int idProduct, int count) {
        this.idProduct = idProduct;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
