package shop.entity;

import java.math.BigInteger;

public class OrderItem extends AbstractEntity<Long> {

    private BigInteger idOrder;
    private Product product;
    private int count;

    public OrderItem(Product product, int count) {
        super();
        this.product = product;
        this.count = count;
    }

    public BigInteger getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(BigInteger idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
