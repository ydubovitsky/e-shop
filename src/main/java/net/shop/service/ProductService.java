package net.shop.service;

import net.shop.entity.impl.Product;

import java.util.List;

public interface ProductService {

    /**
     * Возвращает список всех продуктов
     * @param page - какую страницу продуктов отобразить
     * @param limit - количество продуктов для отображения
     * @return
     */
    List<Product> listAllProducts(int page, int limit);
}
