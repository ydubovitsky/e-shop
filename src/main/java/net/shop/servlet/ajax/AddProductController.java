package net.shop.servlet.ajax;

import net.shop.form.ProductForm;
import net.shop.model.ShoppingCart;
import net.shop.servlet.AbstractController;
import net.shop.util.RoutingUtils;
import net.shop.util.SessionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/json/product/add")
public class AddProductController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm productForm = createProductForm(req);
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        getOrderService().addProductToShoppingCart(productForm, shoppingCart);

        JSONObject jsonObject = new JSONObject(); //? Можно ли вынести в отдельный метод?
        jsonObject.put("totalCount", shoppingCart.getTotalCount());
        jsonObject.put("totalCost", shoppingCart.getTotalCost());
        RoutingUtils.sendJson(jsonObject, resp);
    }
}
