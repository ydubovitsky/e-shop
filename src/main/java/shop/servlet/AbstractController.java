package shop.servlet;

import shop.service.OrderService;
import shop.service.ProductService;
import shop.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {

    private ProductService productService;
    private OrderService orderService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
