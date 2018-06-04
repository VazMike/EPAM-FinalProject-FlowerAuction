package com.viazovski.flowerauction.exception;

/**
 * {@code RepositoryException} is an exception occurring only in repository layer.
 */
public class RepositoryException extends Exception {

    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
