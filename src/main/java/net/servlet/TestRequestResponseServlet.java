package net.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Этот сервле показывает как работает запрос-ответ и как применяются методы GET - POST
 */
@WebServlet("/test-req-resp")
public class TestRequestResponseServlet extends HttpServlet {

    /**
     * Пришел запрос через строку браузера, методом GET, и мы устанавливаем заголовки и тело сообщения (ответа)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setDateHeader("Date", System.currentTimeMillis());
        resp.addHeader("author", "slim shady");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setLocale(Locale.ITALIAN);
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        //TODO Нужно это как то пофиксить? папка root, path и все такое
        out.println("<form action='/e_shop_war_exploded/test-req-resp' method='post'>");
        out.println("Your name: <input name='name'><br>");
        out.println("<input type='submit'>");
        out.println("</body></html>");
        out.close();
    }

    /**
     * Тут уже читаются значения из запроса через форму (метод POST)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(" Request method: "+req.getMethod());
        out.println("<br>Request URI: "+req.getRequestURI());
        out.println("<br>Protocol Version: "+req.getProtocol());
        out.println("<br>Header 'User-Agent': "+req.getHeader("User-Agent"));
        // Работает ли тот заголовок, кторый мы установили в GET
        // Ответ - нет, не работает!
        out.println("<br>Header 'author': "+req.getHeader("author"));
        out.println("<br>Header 'Accept-Language': "+req.getLocale());
        out.println("<br>Header 'Content-Length': "+req.getContentLength());
        out.println("<br>Header 'Content-Type': "+req.getContentType());
        out.println("<br>Remote address: "+req.getRemoteHost());
        out.println("<br>Request parameter: "+req.getParameter("name"));
        out.close();
    }
}
