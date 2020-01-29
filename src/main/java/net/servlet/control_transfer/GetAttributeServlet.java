package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get-attr")
public class GetAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Attribute attribute = (Attribute) req.getAttribute("attribute");
        Attribute sessionAttribute = (Attribute) req.getSession().getAttribute("session");
        PrintWriter writer = resp.getWriter();
        writer.write(attribute.getName() + " " + sessionAttribute.getName());
        writer.close();
    }
}
