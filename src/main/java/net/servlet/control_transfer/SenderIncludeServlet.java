package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sender-inc")
public class SenderIncludeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("before include SenderIncludeServlet ");
        req.getRequestDispatcher("/include").include(req, resp);
        writer.write(" after include SenderIncludeServlet");
        // Не закрывай потоки раньше времени! Если в других сервлетах закроешь, то все в него не записать ничего
        writer.close();
    }
}
