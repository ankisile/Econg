package com.example.bunjang.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotDefinedException extends RuntimeException {
    public UserNotDefinedException() {
        super();
    }
    public UserNotDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotDefinedException(String message) {
        super(message);
    }
    public UserNotDefinedException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}