package net.servlet.practical;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/admin", initParams = {
        @WebInitParam(name = "ip", value = "0:0:0:0:0:0:0:2"),
        @WebInitParam(name = "accessKey", value = "secret"),
        @WebInitParam(name = "login", value = "admin"),
        @WebInitParam(name = "password", value = "password")
})
public class AdminServlet extends HttpServlet {

    private String ip;
    private String accessKey;
    private String login;
    private String password;

    /**
     * Runs when creating the servlet, once. Initializes configuration parameters.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        ip = getServletConfig().getInitParameter("ip");
        accessKey = getServletConfig().getInitParameter("accessKey");
        login = getServletConfig().getInitParameter("login");
        password = getServletConfig().getInitParameter("password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getRemoteAddr();
        String accessKey = req.getHeader("ACCESS_KEY");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            validate(ip, accessKey, login, password);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("OK");
        } catch (IllegalStateException e) {
            resp.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            resp.getWriter().write("FAILED");
        }
    }

    /**
     * Input Validation
     * @param ip, @param accessKey, @param login, @param password - users data for validation.
     */
    private void validate(String ip, String accessKey, String login, String password) {
        if (ip.equals(this.ip)) {
            System.out.println("login via ip " + ip);
        }
        else if (login.equals(this.login) && password.equals(this.password)) {
            System.out.println("login via login " + login + " and password " + password);
        }
        else if (this.accessKey.equals(accessKey)) {
            System.out.println("login via ip " + accessKey);
        }
        else {
            throw new IllegalStateException();
        }
    }
}
