package net.shop.servlet.page;

import net.shop.entity.impl.Product;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static net.shop.Constants.MAX_PRODUCTS_PER_HTML_PAGE;

@WebServlet("/products/*")
public class ProductsByCategoryController extends AbstractController {
    private static final int SUBSTRING_INDEX = "/products".length();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX); //* возвращает строку начинающуюся с индекса SUBSTRING_INDEX
        List<Product> products = getProductService().listProductsByCategory(categoryUrl, 1, MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("products", products);
        int totalCount = getProductService().countProductsByCategories(categoryUrl); //* Количество продуктов данной категории
        req.setAttribute("pageCount", pageCount(totalCount, MAX_PRODUCTS_PER_HTML_PAGE));
        req.setAttribute("selectedCategoryUrl", categoryUrl); //*  Для того чтобы сделать меню активным
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
