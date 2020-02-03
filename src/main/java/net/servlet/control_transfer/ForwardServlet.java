package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Передаем на другой сервел управление
        req.getRequestDispatcher("/receive").forward(req, resp);
        PrintWriter writer = resp.getWriter();
        writer.write("Hello from ForwardServlet");
        writer.close();
        System.out.println("ForwardServlet");
    }
}
