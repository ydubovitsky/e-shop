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
import java.util.stream.Collectors;

@WebServlet("/products")
public class AllProductsController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = getProductService().listAllProducts(1, Constants.MAX_PRODUCT_PER_HTML_PAGE); //* Получаем все продукты из сервиса
        req.setAttribute("products", products); //! Создаем атрибут, куда записываем коллекцию продуктов
        req.setAttribute("categories", allCategoryList(products));
        RoutingUtils.forwardToPage("products.jsp", req, resp); //! Передаем в jsp, где уже работает в цикле с этой коллекцией
    }

    /**
     * Метод возвращает все уникальные категории товаров, которые существуют в БД.
     * @param products
     * @return
     */
    private List<String> allCategoryList(List<Product> products) {
        List<String> categories = products.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toList());
        return categories;
    }
}
