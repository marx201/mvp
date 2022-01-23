package com.openworld.mvp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RouterInvalidStateException extends RuntimeException {
    public RouterInvalidStateException(String message) {
        super(message);
    }
}
