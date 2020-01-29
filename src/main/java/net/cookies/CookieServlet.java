package net.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getServletContext().getRealPath("WEB-INF/web.xml");
        resp.getWriter().write(path);
        Cookie cookie = new Cookie("getCanonicalName", this.getClass().getCanonicalName());
        Cookie cookiePath = new Cookie("getPackageName", this.getClass().getPackageName());
        cookie.setPath("/e_shop_war_exploded/cookie"); //! Куки только для этого адреса
        cookie.setMaxAge(1892160000);
        cookiePath.setMaxAge(1892160000);
        resp.addCookie(cookie);
        resp.addCookie(cookiePath);
    }
}
