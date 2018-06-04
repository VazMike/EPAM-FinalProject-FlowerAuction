package com.viazovski.flowerauction.exception;

/**
 * {@code CommandException} is an exception occurring only in command layer.
 */
public class CommandException extends Exception {

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
