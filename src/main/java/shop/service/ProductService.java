package shop.service;

import shop.entity.Product;

import java.util.List;

/**
 * Serves for products
 */
public interface ProductService {

    /**
     *
     * @param page - какую страницу продуктов отобразить
     * @param limit - максимальное количество продуктов для отображения
     * @return
     */
    List<Product> listAllProducts(int page, int limit);

}
