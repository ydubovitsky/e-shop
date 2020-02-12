package shop.service;

import shop.entity.Category;
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

    List<Product> listProductByCategory(String categoryUrl, int page, int limit);
}
