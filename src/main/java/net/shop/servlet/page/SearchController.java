package net.shop.servlet.page;

import net.shop.entity.impl.Product;
import net.shop.form.SearchForm;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.shop.Constants.MAX_PRODUCTS_PER_HTML_PAGE;

@WebServlet("/search")
public class SearchController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForm searchForm = createSearchForm(req);
        List<Product> products = getProductService().listProductsBySearchForm(searchForm, 1, MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("products", products);
        int totalCount = getProductService().countProductsBySearchForm(searchForm);
        req.setAttribute("pageCount", this.pageCount(totalCount, MAX_PRODUCTS_PER_HTML_PAGE));
        req.setAttribute("productCount", totalCount); // Устанавливает результат поиска
        req.setAttribute("searchForm", searchForm);
        RoutingUtils.forwardToPage("search-result.jsp", req, resp);
    }
}
