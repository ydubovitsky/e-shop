package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Почему то этот класс не получилось настроить через XML
 */
@WebServlet("/include")
public class IncludeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>IncludeServlet</h1>");
        req.getRequestDispatcher("/includable").include(req, resp);
        writer.write("<h1>IncludeServlet</h1>");
    }
}
