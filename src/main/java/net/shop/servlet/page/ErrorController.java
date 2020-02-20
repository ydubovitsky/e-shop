package net.shop.servlet.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;

@WebServlet("/error")
public class ErrorController extends AbstractController {
	private static final long serialVersionUID = -4385792519039493271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoutingUtils.forwardToPage("error.jsp", req, resp);
	}
}
