package net.shop.servlet.page;

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

@WebServlet("/from-social")
public class FromSocialController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            SocialAccount socialAccount = getSocialService().getSocialAccount(code);
            CurrentAccount currentAccount = getOrderService().authenticate(socialAccount);
            SessionUtils.setCurrentAccount(req, currentAccount);
            RoutingUtils.redirect("/my-orders", req, resp);
        } else {
            LOGGER.warn("Parameter code not found");
            RoutingUtils.redirect("/sign-in", req, resp);
        }
    }
}
