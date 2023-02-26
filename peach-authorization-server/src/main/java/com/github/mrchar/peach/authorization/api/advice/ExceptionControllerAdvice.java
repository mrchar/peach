package com.github.mrchar.peach.authorization.api.advice;

import com.github.mrchar.peach.authorization.base.exception.AbstractPeachException;
import com.github.mrchar.peach.authorization.base.exception.UnknownException;
import com.github.mrchar.peach.authorization.base.model.Response;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<Null>> handleException(IllegalArgumentException exception) {
        return new ResponseEntity<>(
                Response.failure("BadRequest", exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AbstractPeachException.class)
    public ResponseEntity<Response<Null>> handleException(AbstractPeachException exception) {
        return new ResponseEntity<>(
                Response.failure(exception.getCode(), exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Null>> handleException(Exception exception) {
        UnknownException e = new UnknownException(exception.getMessage(), exception);
        return new ResponseEntity<>(Response.failure(e.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
