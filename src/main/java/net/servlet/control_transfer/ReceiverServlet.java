package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/receive")
public class ReceiverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("html/text"); Если не закоментить эту строчку будет скачиваться файл
        //! Считываем параметр из сроки запроса
        String a = (String) req.getAttribute("password");
        // Пишем в ответ
        PrintWriter writer = resp.getWriter();
        writer.write("Hello from ReceiverServlet " + a);
        //! req.getRequestDispatcher("/forward").forward(req,resp); бесконечный цикл
        //writer.close();
    }
}
