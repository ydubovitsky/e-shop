package net.shop.entity.impl;

import net.shop.entity.AbstractEntity;

import java.math.BigInteger;

public class OrderItem extends AbstractEntity<BigInteger> {

    private Long idOrder;
    private Product product;
    private int count;

    //TODO Нужны ли тут конструкторы?
    public OrderItem(Product product, int count) {
        super();
        this.product = product;
        this.count = count;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
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
