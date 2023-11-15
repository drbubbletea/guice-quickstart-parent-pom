package net.timeboxing.resource;

public class ClassResourceNotFoundException extends RuntimeException {
    public ClassResourceNotFoundException() {
    }

    public ClassResourceNotFoundException(String message) {
        super(message);
    }

    public ClassResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
