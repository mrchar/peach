package net.mrchar.peach.authorization.common.exception;

public abstract class AbstractPeachException extends RuntimeException {
    private final String code;

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

    public String getCode() {
        return code;
    }
}
