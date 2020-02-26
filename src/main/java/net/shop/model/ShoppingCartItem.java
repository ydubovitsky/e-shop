package net.shop.model;

import net.shop.entity.impl.Product;

import java.io.Serializable;

/**
 * Класс, представляющий собой продукт в корзине
 */
public class ShoppingCartItem implements Serializable {

	private Product product;
	private int count;

	public ShoppingCartItem(Product product, int count) {
		this.product = product;
		this.count = count;
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

	@Override
	public String toString() {
		return "ShoppingCartItem [idProduct=" + product.getId() + ", count=" + count + "]";
	}
}
