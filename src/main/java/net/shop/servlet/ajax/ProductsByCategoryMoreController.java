package net.shop.servlet.ajax;

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

@WebServlet("/ajax/html/more/products/*")
public class ProductsByCategoryMoreController extends AbstractController {

    private static final int SUBSTRING_INDEX = "/ajax/html/product/".length();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryUri = req.getRequestURI().substring(SUBSTRING_INDEX); //! У нас все приложение работает по адресу /
        List<Product> products = getProductService().listProductsByCategory(categoryUri, getPage(req), MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("products", products);
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp); //! Возвращает ФРАГМЕНТ product-list
    }
}
