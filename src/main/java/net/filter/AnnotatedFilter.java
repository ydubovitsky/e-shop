package net.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AnnotatedFilter", urlPatterns = "/*")
public class AnnotatedFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //HttpServletRequest request = (HttpServletRequest) req;
        //request.setAttribute("CURRENT_URL", request.getRequestURI());
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
