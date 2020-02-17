package net.shop.servlet.page;

import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class SearchController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productCount", 24); // Устанавливает результат поиска
        RoutingUtils.forwardToPage("search-result.jsp", req, resp);
    }
}
