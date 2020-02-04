package net.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter extends HttpFilter {

    private final static String auth = "auth";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String auth = (String) request.getSession().getAttribute("auth");
        String requestURI = request.getRequestURI();
        if (auth == null) {
            request.getSession().setAttribute("requestURI", requestURI);
            request.getRequestDispatcher("/sign-in").forward(request, res);
        } else {
            request.getRequestDispatcher(requestURI).forward(req, res);
        }
        //super.doFilter(req, res, chain);
    }
}
