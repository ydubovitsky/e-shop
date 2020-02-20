package net.shop.filter;

import net.shop.entity.impl.Product;
import net.shop.service.ProductService;
import net.shop.service.impl.ServiceManager;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.shop.Constants.CATEGORY_LIST;
import static net.shop.Constants.PRODUCERS_LIST;

/**
 * Этот фильтр срабатывает при каждом запросе клиента!
 *
 * Этот фильтр просто для примера, он не работает. Он не аннотирован
 *
 * Эта реализация фильтра нужна для того, чтобы при каждом запросе возврщать текущие листы и категории из БД,
 * в отличии от Listener, который загружает данные 1 раз при запуске приложения. Но применение фильтра, делает приложение менее быстрым, так как увеличивается число запросов к БД.
 */
public class CategoryProducerFilter extends AbstractFilter {

    private ProductService productService;

    //TODO Где раньше инициализируется productService? В фильре или в слушатетеле?
    //! Ответ: естественно слушатель! БрейкПоинты поставь.
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        productService = ServiceManager.getInstance(filterConfig.getServletContext()).getProductService();
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute(PRODUCERS_LIST, productService.listAllProducers());
        request.setAttribute(CATEGORY_LIST, productService.listAllCategories());
        chain.doFilter(request, response);
    }
}
