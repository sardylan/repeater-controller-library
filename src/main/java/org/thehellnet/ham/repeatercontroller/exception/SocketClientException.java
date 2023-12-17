package org.thehellnet.ham.repeatercontroller.exception;

public class SocketClientException extends RepeaterControllerClientException {

    public SocketClientException() {
    }

    public SocketClientException(String message) {
        super(message);
    }

    public SocketClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocketClientException(Throwable cause) {
        super(cause);
    }

    public SocketClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
