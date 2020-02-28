package net.shop.model;

/**
 * Этот класс служит как абстракцию социального аккаунта из ЛЮБОЙ сети, где есть имя и почта.
 */
public class SocialAccount {

    private final String name;
    private final String email;

    public SocialAccount(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
