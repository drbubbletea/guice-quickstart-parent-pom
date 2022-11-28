package net.timeboxing.jobs.quartz;

public class AppSchedulerException extends RuntimeException {

    public AppSchedulerException() {
    }

    public AppSchedulerException(String message) {
        super(message);
    }

    public AppSchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppSchedulerException(Throwable cause) {
        super(cause);
    }

    public AppSchedulerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
