package net.mrchar.peach.authorization.common.exception;

public class BadRequestException extends AbstractPeachException {
    public static final String CODE = "bad_request";

    public BadRequestException() {
        super(CODE);
    }

    public BadRequestException(String message) {
        super(CODE, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}
