package shop.servlet.ajax;

import shop.constants.Constants;
import shop.entity.Product;
import shop.servlet.AbstractController;
import shop.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for loading new products when the "Load more" button is clicked.
 */
@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {

    public static final int SUBSTRING_INDEX = "/ajax/html/more/products".length();

    //TODO Разобраться как работает этот сервлет и ajax
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        List<Product> products = getProductService().listProductByCategory(categoryUrl, 2, Constants.MAX_PRODUCT_PER_HTML_PAGE);
        req.setAttribute("products", products);
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp); //! Что возвращает эта функция?
    }
}
