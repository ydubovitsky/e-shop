package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Этот сервлет передает управление на JSP
 */
@WebServlet("/jsp-forward")
public class ForwardJSP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", this.getClass().getCanonicalName());
        PrintWriter writer = resp.getWriter();
        req.getRequestDispatcher("WEB-INF/jsp/view.jsp").forward(req, resp);
//        req.getRequestDispatcher("WEB-INF/jsp/view.jsp").include(req, resp);
        writer.println("Hello my little friend!");
        writer.write("fsdogdsgkpsdog");
        System.out.println("sdignoiwrhniowhboiwrjboirejoirjoerjhoierjiorejhoirejhioerjhoiej");
        writer.close();
    }
}
