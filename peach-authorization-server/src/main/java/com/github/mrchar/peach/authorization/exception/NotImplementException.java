package com.github.mrchar.peach.authorization.exception;

public class NotImplementException extends AbstractPeachException {
    public static final String CODE = "NotImplement";

    protected NotImplementException() {
        super(CODE);
    }

    protected NotImplementException(String message) {
        super(CODE, message);
    }
}
