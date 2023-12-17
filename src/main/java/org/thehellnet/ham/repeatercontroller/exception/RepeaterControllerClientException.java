package org.thehellnet.ham.repeatercontroller.exception;

public class RepeaterControllerClientException extends RuntimeException {

    public RepeaterControllerClientException() {
    }

    public RepeaterControllerClientException(String message) {
        super(message);
    }

    public RepeaterControllerClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeaterControllerClientException(Throwable cause) {
        super(cause);
    }

    public RepeaterControllerClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
