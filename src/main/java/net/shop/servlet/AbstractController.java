package net.shop.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import net.shop.service.OrderService;
import net.shop.service.ProductService;
import net.shop.service.impl.ServiceManager;

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = -2031074947573473708L;

	private ProductService productService;
	private OrderService orderService;

	@Override
	public final void init() throws ServletException {
		productService = ServiceManager.getInstance(getServletContext()).getProductService();
		orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
	}

	public final ProductService getProductService() {
		return productService;
	}

	public final OrderService getOrderService() {
		return orderService;
	}

	/**
	 * Функция подсчитывает количество страниц с продуктами.
	 * @param totalCount - общее количество продуктов
	 * @param itemsPerPage - продуктов на странице
	 * @return
	 */
	public final int pageCount(int totalCount, int itemsPerPage) {
		int result = totalCount / itemsPerPage;
		//! Допустим totalCount = 14, а itemsPerPage = 12. В результате деления мы получим 1 => 2 продукта пропадут в результате округления и нужно ввести еще 1 доп страницу для отображения этих 2х объектов.
		if (result * itemsPerPage != totalCount) {
			result++;
		}
		return result;
	}

	/**
	 * Возвращает номер страницы из строки запроса
	 * @param request
	 * @return
	 */
	public final int getPage(HttpServletRequest request) {
		try {
			Integer page = Integer.parseInt(request.getParameter("page"));
			return page;
		} catch (NumberFormatException e) {
			return 1;
		}
	}
}
