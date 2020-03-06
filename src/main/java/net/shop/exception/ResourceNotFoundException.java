package net.shop.exception;

public class ResourceNotFoundException extends IllegalArgumentException {

    public ResourceNotFoundException(String s) {
        super(s);
    }
}
