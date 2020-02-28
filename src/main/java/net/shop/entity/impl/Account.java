package net.shop.entity.impl;

import net.shop.entity.AbstractEntity;
import net.shop.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount {

    private String name;
    private String email;

    public Account() {
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getDescription() {
        return name + " " + email;
    }
}
