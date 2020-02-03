package shop.servlet;

import shop.exception.ValidationException;
import shop.model.Attributes;
import shop.model.ShoppingCart;
import shop.model.ShoppingCartSerializator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopping-cart-servlet")
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartSerializator shoppingCartSerializator = new ShoppingCartSerializator();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(String.valueOf(Attributes.CURRENT_SHOPPING_CART));
        Cookie[] cookies = req.getCookies();
        if (shoppingCart == null || cookies.length == 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(String.valueOf(Attributes.CURRENT_SHOPPING_CART))) {
                    shoppingCart = shoppingCartSerializator.shoppingCartFromString(cookie.getValue());
                }
            }
            req.getSession().setAttribute(String.valueOf(Attributes.CURRENT_SHOPPING_CART), shoppingCart);
        } else {
            try {
                shoppingCart.addProduct(1235,262362);
                String s = shoppingCartSerializator.shoppingCartToString(shoppingCart);
                Cookie cookie = new Cookie(String.valueOf(Attributes.CURRENT_SHOPPING_CART), s);
                resp.addCookie(cookie);
            } catch (ValidationException e) {
                System.out.println("ValidationException");
                resp.getWriter().write("fsdiognoingreoigjoijeojeohj4oph");
            }
        }
    }
}
