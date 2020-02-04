package shop.constant;

public enum Attributes {
    CURRENT_SHOPPING_CART;

    @Override
    public String toString() {
        return this.name();
    }
}
