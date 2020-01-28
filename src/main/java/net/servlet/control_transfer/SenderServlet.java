package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sender")
public class SenderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // "Вставляем другой сервлет" не полчается так
        // req.getRequestDispatcher("/include").include(req, resp);
        // Передаем на другой сервел управление
        req.getRequestDispatcher("/receive").forward(req, resp);
    }
}
