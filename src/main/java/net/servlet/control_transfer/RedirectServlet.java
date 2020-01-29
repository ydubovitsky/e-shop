package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * При редиректе нельзя получить значения атрибутов, так как фактически идет 2 запроса-ответа
 *
 * Но мы можем передать параметры через строку запроса (указанную в редиректе)
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //? Почему тут нужно указывать путь относительно пакета, а в requestDispatcher нет
        resp.sendRedirect("/e_shop_war_exploded/receive?a=1&a=2&a=3&password=23tgf423gq43");
    }
}
