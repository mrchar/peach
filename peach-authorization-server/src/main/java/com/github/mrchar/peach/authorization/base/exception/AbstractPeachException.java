package com.github.mrchar.peach.authorization.base.exception;

import lombok.Getter;

public abstract class AbstractPeachException extends RuntimeException {
    @Getter
    private String code;

    protected AbstractPeachException(String code) {
        this.code = code;
    }

    protected AbstractPeachException(String code, String message) {
        super(message);
        this.code = code;
    }

    protected AbstractPeachException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
