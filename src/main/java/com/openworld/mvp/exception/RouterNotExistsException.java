package com.openworld.mvp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouterNotExistsException extends RuntimeException {
    public RouterNotExistsException(String message) {
        super(message);
    }
}
