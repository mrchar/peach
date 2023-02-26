package com.github.mrchar.peach.authorization.base.exception;

public class UnknownException extends AbstractPeachException {
    public static final String CODE = "UnknownException";

    public UnknownException() {
        super(CODE);
    }

    public UnknownException(String message) {
        super(CODE, message);
    }

    public UnknownException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
