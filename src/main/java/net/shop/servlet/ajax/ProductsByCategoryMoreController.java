package net.shop.servlet.ajax;

import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/products/*")
public class ProductsByCategoryMoreController extends AbstractController {

    private static final int SUBSTRING_INDEX = "/ajax/html/product/".length();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryUri = req.getRequestURI().substring(SUBSTRING_INDEX); //! У нас все приложение работает по адресу /
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp); //! Возвращает ФРАГМЕНТ product-list
    }
}
