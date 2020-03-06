package net.shop.servlet.page;

import net.shop.Constants;
import net.shop.entity.impl.Order;
import net.shop.model.CurrentAccount;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;
import net.shop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-orders")
public class MyOrdersController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentAccount account = SessionUtils.getCurrentAccount(req);
        List<Order> orderList = getOrderService().listMyOrders(account, 1, Constants.ORDERS_PER_PAGE);
        req.setAttribute("orders", orderList);
        int orderCount = getOrderService().countMyOrders(account);
        req.setAttribute("pageCount", pageCount(orderCount, Constants.ORDERS_PER_PAGE));
        RoutingUtils.forwardToPage("my-orders.jsp", req, resp);
    }
}
