package net.shop.service;

import net.shop.entity.impl.Category;
import net.shop.entity.impl.Producer;
import net.shop.entity.impl.Product;
import net.shop.form.SearchForm;

import java.util.List;

public interface ProductService {

    /**
     * Возвращает список всех продуктов
     * @param page - какую страницу продуктов отобразить
     * @param limit - количество продуктов для отображения
     * @return
     */
    List<Product> listAllProducts(int page, int limit);

    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);

    /**
     * Возвращает список всех категорий
     * @return
     */
    List<Category> listAllCategories();

    /**
     * Возвращает список всех категорий из БД
     * @return
     */
    List<Producer> listAllProducers();

    /**
     * Возвращает количество всех продуктов из БД
     * @return
     */
    int countAllProducts();

    /**
     * Возвращает количество продуктов КОНКРЕТНОЙ категории
     * @param category
     * @return
     */
    int countProductsByCategories(String category);

    /**
     * Получаем список продуктов удовлетворяющих запросу из формы для поиска
     */
    List<Product> listProductsBySearchForm(SearchForm searchForm, int page, int limit);

    /**
     * Получает количество продуктов, удовлетворяющих поисковому запросу.
     */
    int countProductsBySearchForm(SearchForm searchForm);
}
