package com.github.mrchar.peach.authorization.exception;

import lombok.Getter;

public abstract class AbstractPeachException extends RuntimeException {
    @Getter
    private String code;

    protected AbstractPeachException(String code) {
        this.code = code;
    }

    public AbstractPeachException(String code, String message) {
        super(message);
        this.code = code;
    }
}
