package net.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CookiesUtils {

    /**
     * Ищет cookie и возвращает если удовлетворяет условиям проверки
     * @param req
     * @param name
     * @return
     */
    public static Cookie findCookie(HttpServletRequest req, String name) {
        Cookie result = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    if (c.getValue() != null && !"".equals(c.getName())) {
                        result = c;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Create cookie and sets some parameters.
     * @param req
     * @param age
     * @param name
     * @param value
     */
    public static void setCookieValues(HttpServletRequest req, int age, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(age);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setComment("some comment from setCookieValues");
    }

}
