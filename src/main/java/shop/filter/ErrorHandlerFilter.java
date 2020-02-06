package shop.filter;

import shop.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Throwable th) {
            String requestUrl = servletRequest.getRequestURI();
            LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
            RoutingUtils.forwardToPage("error.jsp", servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
