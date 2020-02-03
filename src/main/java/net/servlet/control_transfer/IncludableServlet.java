package net.servlet.control_transfer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//TODO Внимание! Если мы закроем поток resp.getWriter(); - то все, сервлеты после этого ничего писать не будут
@WebServlet("/includable")
public class IncludableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" +
                "    <title>Bootstrap 101 Template</title>\n" +
                "\n" +
                "    <!-- Bootstrap -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\" integrity=\"sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n" +
                "    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n" +
                "    <!--[if lt IE 9]>\n" +
                "      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" +
                "      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" +
                "    <![endif]-->\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>Hello, world!</h1>\n" +
                "    <div class=\"alert alert-primary\" role=\"alert\">\n" +
                "      This is a primary alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-secondary\" role=\"alert\">\n" +
                "      This is a secondary alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-success\" role=\"alert\">\n" +
                "      This is a success alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-danger\" role=\"alert\">\n" +
                "      This is a danger alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-warning\" role=\"alert\">\n" +
                "      This is a warning alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-info\" role=\"alert\">\n" +
                "      This is a info alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-light\" role=\"alert\">\n" +
                "      This is a light alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-dark\" role=\"alert\">\n" +
                "      This is a dark alert with <a href=\"#\" class=\"alert-link\">an example link</a>. Give it a click if you like.\n" +
                "    </div>\n" +
                "    <div class=\"alert alert-success\" role=\"alert\">\n" +
                "      <h4 class=\"alert-heading\">Well done!</h4>\n" +
                "      <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>\n" +
                "      <hr>\n" +
                "      <p class=\"mb-0\">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>\n" +
                "    </div>\n" +
                "    <div class=\"dropdown-menu\">\n" +
                "      <form class=\"px-4 py-3\">\n" +
                "        <div class=\"form-group\">\n" +
                "          <label for=\"exampleDropdownFormEmail1\">Email address</label>\n" +
                "          <input type=\"email\" class=\"form-control\" id=\"exampleDropdownFormEmail1\" placeholder=\"email@example.com\">\n" +
                "        </div>\n" +
                "        <div class=\"form-group\">\n" +
                "          <label for=\"exampleDropdownFormPassword1\">Password</label>\n" +
                "          <input type=\"password\" class=\"form-control\" id=\"exampleDropdownFormPassword1\" placeholder=\"Password\">\n" +
                "        </div>\n" +
                "        <div class=\"form-check\">\n" +
                "          <input type=\"checkbox\" class=\"form-check-input\" id=\"dropdownCheck\">\n" +
                "          <label class=\"form-check-label\" for=\"dropdownCheck\">\n" +
                "            Remember me\n" +
                "          </label>\n" +
                "        </div>\n" +
                "        <button type=\"submit\" class=\"btn btn-primary\">Sign in</button>\n" +
                "      </form>\n" +
                "      <div class=\"dropdown-divider\"></div>\n" +
                "      <a class=\"dropdown-item\" href=\"#\">New around here? Sign up</a>\n" +
                "      <a class=\"dropdown-item\" href=\"#\">Forgot password?</a>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n" +
                "    <script src=\"https://code.jquery.com/jquery-1.12.4.min.js\" integrity=\"sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ\" crossorigin=\"anonymous\"></script>\n" +
                "    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" +
                "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\" integrity=\"sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd\" crossorigin=\"anonymous\"></script>\n" +
                "  </body>\n" +
                "</html>");
        //! writer.close(); Не закрываем поток
    }
}
