package net.shop.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
	}
}
