package com.viazovski.flowerauction.exception;

/**
 * {@code ConnectionException} is an exception occurring only in connection layer.
 * Even though custom exceptions are strongly recommended to be checked ones
 * there's no feasible way to get around the problem but to report fatal error.
 */
public class ConnectionException extends RuntimeException {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
