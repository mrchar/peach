package net.mrchar.peach.authorization.api.advice;

import net.mrchar.peach.authorization.common.exception.AbstractPeachException;
import net.mrchar.peach.authorization.common.exception.BadRequestException;
import net.mrchar.peach.authorization.common.exception.UnknownException;
import net.mrchar.peach.authorization.common.model.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageResponse> handleException(BadRequestException exception) {
        return new ResponseEntity<>(
                MessageResponse.failure(exception.getCode(), exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleException(IllegalArgumentException exception) {
        BadRequestException e = new BadRequestException(exception.getMessage(), exception);
        return new ResponseEntity<>(
                MessageResponse.failure(e.getCode(), exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AbstractPeachException.class)
    public ResponseEntity<MessageResponse> handleException(AbstractPeachException exception) {
        return new ResponseEntity<>(
                MessageResponse.failure(exception.getCode(), exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleException(Exception exception) {
        UnknownException e = new UnknownException(exception.getMessage(), exception);
        return new ResponseEntity<>(MessageResponse.failure(e.getCode(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
