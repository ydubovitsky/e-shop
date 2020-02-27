package net.shop.servlet;

import net.shop.form.ProductForm;
import net.shop.model.ShoppingCart;
import net.shop.util.RoutingUtils;
import net.shop.util.SessionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractProductController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm productForm = createProductForm(req); //* Восстанавливаем productForm из реквеста
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        processProductForm(productForm, shoppingCart, req, resp);
    }

    /**
     * Этот метод выполняет какие любо действия с ProductForm form, ShoppingCart cart
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void processProductForm(ProductForm form, ShoppingCart cart, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected void sendResponse(ShoppingCart cart, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", cart.getTotalCount());
        jsonObject.put("totalCost", cart.getTotalCost());
        RoutingUtils.sendJson(jsonObject, resp);
    }
}
