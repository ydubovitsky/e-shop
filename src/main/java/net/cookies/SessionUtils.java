package net.cookies;

import net.model.Constants;
import shop.model.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс для работы с сессией
 */
public class SessionUtils {

    public static ShoppingCart getCurrentShoppingCart(HttpServletRequest request) {
        //! Нужно заменить на enum
        ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
        if (shoppingCart == null) { // Если нет существующей корзины, создаем ее и сет-аем
            ShoppingCart createdShoppingCart = new ShoppingCart();
            setCurrentShoppingCart(createdShoppingCart, request);
        }
        return shoppingCart;
    }

    public static void setCurrentShoppingCart(ShoppingCart shoppingCart, HttpServletRequest request) {
        request.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
    }

    public static void clearCurrentShoppingCart(HttpServletResponse response, HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
        CookiesUtils.setCookieValues(request, 0, null, null); // вызываем утилитный метод
    }

    public static boolean isCurrentShoppingCart(ShoppingCart shoppingCart, HttpServletRequest request) {
        return request.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
    }

//    public static boolean findShoppingCart(ShoppingCart shoppingCart, HttpServletRequest request) {
//        CookiesUtils.findCookie(request, )
//    }


//        public static void updateCurrentShoppingCart(HttpServletResponse response, String cookieValue) {
//        response.getS
//    }
}
