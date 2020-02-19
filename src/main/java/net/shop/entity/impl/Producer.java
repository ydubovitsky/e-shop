package net.shop.entity.impl;

import net.shop.entity.AbstractEntity;

public class Producer extends AbstractEntity<Integer> {

    private String name;
    private int productCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return productCount;
    }

    public void setCount(int count) {
        this.productCount = count;
    }
}
