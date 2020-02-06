package shop.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class RoutingUtils {

    public static void forwardToFragment(String jspFragment, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspFragment).forward(req, resp);
    }

    /**
     * This method forwards the control to the page-template.jsp into which jsp is embedded from the arguments of the String jspPage method!
     * @param jspPage - page which needed do forward
     */
    public static void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Сетаем значение currentPage, а затем этот атрибут используется в page-template
        req.setAttribute("currentPage", "page/" + jspPage); //! Посмотри как работает page-template.jsp и будет ясно все с методом
        req.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(req, resp);
    }

    public static void sendHTMLFragment(String text, HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/html");
        resp.getWriter().println(text);
        resp.getWriter().close();
    }

    public static void redirect(String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(url);
    }
}
