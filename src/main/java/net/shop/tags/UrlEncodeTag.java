package net.shop.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Этот тэг служит для того, чтобы закодироват всю строку запроса!
 */
public class UrlEncodeTag extends SimpleTagSupport {

    private String var;
    private String url;

    @Override
    public void doTag() throws JspException, IOException {
        String encodeUrl = URLEncoder.encode(url, "UTF-8");
        getJspContext().setAttribute(var, encodeUrl);
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
