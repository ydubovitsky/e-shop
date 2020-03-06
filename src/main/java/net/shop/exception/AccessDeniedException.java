package net.shop.exception;

public class AccessDeniedException extends IllegalArgumentException {

    public AccessDeniedException(String s) {
        super(s);
    }
}
