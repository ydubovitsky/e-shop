package net.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.shop.Constants;
import net.shop.entity.impl.Product;
import net.shop.exception.ValidationException;

public class ShoppingCart implements Serializable {

	/**
	 * Данное поле содержит id добавленного продукта и ссылку на shoppingCartItem
	 */
	private Map<Integer, ShoppingCartItem> products = new LinkedHashMap<>(); //* Сохраняет порядок добавления
	private int totalCount = 0;
	private BigDecimal totalCost = BigDecimal.ZERO;

	/**
	 * Метод добавляет продукт в корзину.
	 * @param product - продукт
	 * @param count - количество
	 */
	public void addProduct(Product product, int count) {
		validateShoppingCartSize(product.getId());
		ShoppingCartItem shoppingCartItem = products.get(product.getId()); //! если в products уже есть продукт с данным id
		if (shoppingCartItem == null) { //* если нет
			validateProductCount(count);
			shoppingCartItem = new ShoppingCartItem(product, count);
			products.put(product.getId(), shoppingCartItem);
		} else { //! если есть, изменяем количество
			validateProductCount(count + shoppingCartItem.getCount());
			shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
		}
		refreshStatistics();
	}

	public void removeProduct(Integer idProduct, int count) {
		ShoppingCartItem shoppingCartItem = products.get(idProduct);
		if (shoppingCartItem != null) {
			if (shoppingCartItem.getCount() > count) {
				shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
			} else {
				products.remove(idProduct);
			}
			refreshStatistics();
		}
	}

	public Collection<ShoppingCartItem> getItems() {
		return products.values();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	private void validateProductCount(int count) {
		if(count > Constants.MAX_PRODUCT_COUNT_PER_SHOPPING_CART){
			throw new ValidationException("Limit for product count reached: count="+count);
		}
	}

	private void validateShoppingCartSize(int idProduct){
		if(products.size() > Constants.MAX_PRODUCTS_PER_SHOPPING_CART ||
				(products.size() == Constants.MAX_PRODUCTS_PER_SHOPPING_CART && !products.containsKey(idProduct))) {
			throw new ValidationException("Limit for ShoppingCart size reached: size="+products.size());
		}
	}

	/**
	 * Метод обновляет состояние корзины, сумарную цену и количество товаров
	 */
	private void refreshStatistics() {
		totalCount = 0; //? Зачем? когда в переменной класса уже объявлено
		totalCost = BigDecimal.ZERO;
		for (ShoppingCartItem shoppingCartItem : getItems()) {
			totalCount += shoppingCartItem.getCount();
			totalCost = totalCost.add(
					shoppingCartItem.getProduct().getPrice()
							.multiply(
								BigDecimal.valueOf(shoppingCartItem.getCount())
							)
			);
		}
	}

	@Override
	public String toString() {
		return String.format("ShoppingCart [products=%s, totalCount=%s]", products, totalCount);
	}
}
