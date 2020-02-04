package net.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

public class XMLConfigFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Hello");
        chain.doFilter(req, res);
        System.out.println("gisgoiwjgrwoijgwoi");
    }
}
