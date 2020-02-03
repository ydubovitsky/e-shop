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
        Cookie cookie = createCookie("param", "value", "/", 1234567890);
        resp.addCookie(cookie);
        readCookies(req);
        outputRealPath("/WEB-INF/web.xml");
    }

    /**
     * Create cookie
     * @param name
     * @param value
     * @param path - путь по которому будут доступны куки
     * @param age - if 0 - delete cookie
     * @return
     */
    private Cookie createCookie(String name, String value, String path, int age) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path); // путь по которому будут доступны куки
        cookie.setMaxAge(age); // live time
        cookie.setHttpOnly(true); // только на уровне http, js не может получить к ним доступ
        return cookie;
    }

    /**
     * Read name and value of cookie
     * @param req
     */
    private void readCookies(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for(int i = 0; i < cookies.length; i++) {
            System.out.println("name = " + cookies[i].getName() + " value = " + cookies[i].getValue());
        }
    }

    /**
     * Output real system path of file.
     * @param relativePath
     */
    private void outputRealPath(String relativePath) {
        System.out.println(getServletContext().getRealPath(relativePath));
    }
}
