package com.github.mrchar.peach.authorization.exception;

import com.github.mrchar.peach.authorization.base.exception.AbstractPeachException;

public class NotImplementException extends AbstractPeachException {
    public static final String CODE = "NotImplement";

    protected NotImplementException() {
        super(CODE);
    }

    protected NotImplementException(String message) {
        super(CODE, message);
    }
}
