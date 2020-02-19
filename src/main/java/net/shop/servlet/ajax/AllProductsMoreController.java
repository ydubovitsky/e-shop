package net.shop.servlet.ajax;

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

/**
 *
 */
@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = getProductService().listAllProducts(2, MAX_PRODUCTS_PER_HTML_PAGE); //! Нужно поправить статическую page = 2
		req.setAttribute("products", products);
		RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
	}
}
