package net.shop.entity.impl;

import net.shop.entity.AbstractEntity;

import java.math.BigInteger;

public class OrderItem extends AbstractEntity<Long> {

    private Long idOrder;
    private Product product;
    private int count;

    /**
     * переменная this ссылается на текущий экземпляр класса, в котором она используется, тогда как super — на текущий экземпляр родительского класса.
     *
     * Каждый конструктор при отсутствии явных вызовов других конструкторов неявно вызывает с помощью super() конструктор
     * без аргументов родительского класса, при этом у вас всегда остается возможность явно вызвать любой другой конструктор с помощью либо this(), либо super().
     */
    public OrderItem() {
        super(); //? Зачем тут супер
    }

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
