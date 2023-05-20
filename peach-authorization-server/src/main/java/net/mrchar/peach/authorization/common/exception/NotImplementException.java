package net.mrchar.peach.authorization.common.exception;

public class NotImplementException extends AbstractPeachException {
    public static final String CODE = "not_implement";

    protected NotImplementException() {
        super(CODE);
    }

    protected NotImplementException(String message) {
        super(CODE, message);
    }
}
