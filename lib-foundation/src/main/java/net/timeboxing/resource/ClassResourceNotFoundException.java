package net.timeboxing.resource;

public class ClassResourceNotFoundException extends RuntimeException {
    public ClassResourceNotFoundException() {
    }

    public ClassResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
