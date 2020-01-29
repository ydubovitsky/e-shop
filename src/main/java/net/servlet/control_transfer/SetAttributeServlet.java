package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/set-attr")
public class SetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // set attribute
        req.setAttribute("attribute", new Attribute("For mama Russia"));
        // set session param
        req.getSession().setAttribute("session", new Attribute("Red Alert"));
        //forward
        req.getRequestDispatcher("/get-attr").forward(req, resp);
    }
}
