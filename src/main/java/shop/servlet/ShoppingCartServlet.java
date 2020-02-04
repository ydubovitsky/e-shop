package shop.servlet;

import shop.entity.ShoppingCart;
import shop.service.ShoppingCartSerializator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO Вынести логику в функции, добавить константы! Упростить код для понимания.
@WebServlet("/shopping-cart-servlet")
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart a = (ShoppingCart) req.getSession().getAttribute("a");
        ShoppingCartSerializator serializator = new ShoppingCartSerializator();
        boolean flag = true;
        if (a == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("a".equals(cookie.getName())) {
                        a = serializator.shoppingCartFromString(cookie.getValue());
                        req.getSession().setAttribute("a",a);
                        flag = false;
                    }
                }
            }
        }
        if (a != null) {
            String cartString = serializator.shoppingCartToString(a);
            System.out.println(cartString);
            Cookie cookie = new Cookie("a", cartString);
            resp.addCookie(cookie);
        }
        if (flag){
            a = new ShoppingCart();
            a.addProduct(1,5);
            a.addProduct(2,6);
            a.addProduct(3,7);
            req.getSession().setAttribute("a", a);
        }
    }
}
