package net.servlet.practical;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(value="/admin", initParams = {
        @WebInitParam(name = "IP", value = "0:0:0:0:0:0:0:1"),
        @WebInitParam(name = "login", value = "admin"),
        @WebInitParam(name = "password", value = "password")
})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (checkingIP(req)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            System.out.println("Login via ip " + getInitParameter("IP"));
        }

        if (checkingParameters(req, getInitParameterNames())) {
            System.out.println("Login via " + getInitParameter("login") + getInitParameter("password"));
        }
    }

    private boolean checkingIP(HttpServletRequest req) {
        boolean result = false;
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
            if (ipAddress.equals(getServletConfig().getInitParameter("IP"))) {
                result = true;
            }
        }
        return result;
    }

    private boolean checkingParameters(HttpServletRequest req, Enumeration<String> initParameterNames) {
        List<String> list = new ArrayList<>();
        while (initParameterNames.hasMoreElements()) {
            list.add(initParameterNames.nextElement());
        }
        String[] params = req.getParameterValues("param");
        List<String> collect = Stream.of(params).collect(Collectors.toList());
        return collect.containsAll(list);
    }
}
