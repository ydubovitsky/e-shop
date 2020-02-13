package shop.servlet.page;

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

@WebServlet("/products")
public class AllProductsController extends AbstractController {

    private static final int SUBSTRING_INDEX = "/products".length();

    //TODO Нужно корректно заполнить базу!!!
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        List<Product> products = getProductService().listProductByCategory(categoryUrl,1, Constants.MAX_PRODUCT_PER_HTML_PAGE); //* Получаем все продукты из сервиса
        req.setAttribute("products", products); //! Создаем атрибут, куда записываем коллекцию продуктов
        req.setAttribute("selectedCategoryUrl", categoryUrl);
        RoutingUtils.forwardToPage("products.jsp", req, resp); //! Передаем в jsp, где уже работает в цикле с этой коллекцией
    }
}
