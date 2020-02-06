package shop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shop.util.UrlUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public abstract class AbstractFilter implements Filter {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass()); //? какой логур тут используется? зачем нам ch.qos.logback?

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestURL = req.getRequestURI(); //* Проверка, если в uri идет обращение к меди или статик контенту, фильтр пропустит запрос дальше, иначе выполнит обработку
        if (UrlUtils.isMediaUrl(requestURL) || UrlUtils.isStaticUrl(requestURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            doFilter(req, resp, filterChain);
        }
    }

    public abstract void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }
}
