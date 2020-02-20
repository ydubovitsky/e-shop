package net.shop.servlet.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shop.entity.impl.Product;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

import static net.shop.Constants.MAX_PRODUCTS_PER_HTML_PAGE;

@WebServlet("/products")
public class AllProductsController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = getProductService().listAllProducts(1, MAX_PRODUCTS_PER_HTML_PAGE);
		req.setAttribute("products", products); //* Полученный список передаем на уровень представления
		int totalCount = getProductService().countAllProducts();
		req.setAttribute("pageCount", this.pageCount(totalCount, MAX_PRODUCTS_PER_HTML_PAGE)); //* Передаем количество страниц с продуктами
		RoutingUtils.forwardToPage("products.jsp", req, resp);
	}
}
