package net.shop.servlet.page;

import net.shop.Constants;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;
import net.shop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtils.isCurrentAccountCreated(req)) {
            RoutingUtils.redirect("/my-orders", req, resp);
        } else {
            RoutingUtils.forwardToPage("sign-in.jsp", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtils.isCurrentAccountCreated(req)) {
            RoutingUtils.redirect("/my-orders", req, resp);
        } else {
            //! Редирект на фейсбук
            String targetUrl = req.getParameter("target");
            if (targetUrl != null) {
                //! Вся эта пляска нужна только для того чтобы после того как залогинились перейти на тот адрес, который пользователь вводил с параметрами или без
                req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGIN, targetUrl);
            }
            RoutingUtils.redirect(getSocialService().getAuthorizeUrl(),req,resp);
            //! Тут что важно, что в слушателе создается serviceManager, в котором в конструкторе создаются остальные сервисы, которые уже дальше используются в AbstractController
        }
    }
}
