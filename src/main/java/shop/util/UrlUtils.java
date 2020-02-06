package shop.util;

/**
 * This utility class is designed to add prefixes to requests of various types
 */
public class UrlUtils {

    public static boolean isAjaxUrl(String url) {
        return url.startsWith("/ajax/");
    }

    public static boolean isAjaxJsonUrl(String url) {
        return url.startsWith("/ajax/json/");
    }

    public static boolean isAjaxHtmlUrl(String url) {
        return url.startsWith("/ajax/html/");
    }

    public static boolean isStaticUrl(String url) {
        return url.startsWith("/static/");
    }

    public static boolean isMediaUrl(String url) {
        return url.startsWith("/ajax/");
    }

    private UrlUtils() {

    }
}
