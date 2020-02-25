package net.shop.form;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс представляющий собой строку поискового запроса из формы поиска
 */
public class SearchForm {

    private String query; //* Пользовательский запрос
    private List<Integer> producers; //* Список выбранных производителей
    private List<Integer> categories; //* Список выбранных категорий

    public SearchForm(String query, String[] producers, String[] categories) { //? Почему массив? Потому что req.getParameterValues() - возвращает массив, а не список.
        this.query = query;
        this.producers = arrayToList(producers);
        this.categories = arrayToList(categories);
    }

    /**
     * Преобразует массив в список
     */
    private List<Integer> arrayToList(String[] array) {
        if (array != null) {
            return List.of(array).stream().map(Integer::parseInt).collect(Collectors.toList());
        } return Collections.emptyList();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getProducers() {
        return producers;
    }

    public void setProducers(List<Integer> producers) {
        this.producers = producers;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public boolean isCategoriesEmpty() {
        return categories.isEmpty();
    }

    public boolean isProducersEmpty() {
        return producers.isEmpty();
    }
}
