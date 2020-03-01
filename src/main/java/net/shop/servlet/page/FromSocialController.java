package net.shop.servlet.page;

import net.shop.Constants;
import net.shop.model.CurrentAccount;
import net.shop.model.SocialAccount;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;
import net.shop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/from-social")
public class FromSocialController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            SocialAccount socialAccount = getSocialService().getSocialAccount(code);
            CurrentAccount currentAccount = getOrderService().authenticate(socialAccount);
            SessionUtils.setCurrentAccount(req, currentAccount);
            redirectToSuccessPage(req, resp);
        } else {
            LOGGER.warn("Parameter code not found");
            RoutingUtils.redirect("/sign-in", req, resp);
        }
    }

    /**
     * Метод определяет куда делать редирект
     */
    protected void redirectToSuccessPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String targetUrl = (String) req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGIN); //* Если в атрибуте была ссылку куда хотел перейти не залогининый клиент, то он перейдет по ней
        if (targetUrl != null) {
            req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGIN);
            RoutingUtils.redirect(URLDecoder.decode(targetUrl, "UTF-8"), req, resp); //! URLDecoder - нужно для того чтобы весь запрос закодировать а не только параметры
        } else {
            RoutingUtils.redirect("/my-orders", req, resp);
        }
    }
}
