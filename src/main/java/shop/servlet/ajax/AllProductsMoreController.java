package shop.servlet.ajax;

import shop.servlet.AbstractController;
import shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is responsible for loading new products when the "Load more" button is clicked.
 */
@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {
    //TODO Разобраться как работает этот сервлет и ajax
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp); //! Что возвращает эта функция?
    }
}
