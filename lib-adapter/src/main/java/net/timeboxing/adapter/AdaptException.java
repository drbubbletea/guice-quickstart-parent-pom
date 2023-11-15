package net.timeboxing.adapter;

public class AdaptException extends RuntimeException {

    public AdaptException() {
        super();
    }

    public AdaptException(String message) {
        super(message);
    }

    public AdaptException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdaptException(Throwable cause) {
        super(cause);
    }

    protected AdaptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
