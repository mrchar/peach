package net.mrchar.peach.authorization.common.exception;

public class UnknownException extends AbstractPeachException {
    public static final String CODE = "unknown_exception";

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
