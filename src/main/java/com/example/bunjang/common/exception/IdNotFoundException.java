package com.example.bunjang.common.exception;

import org.springframework.http.HttpStatus;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException() {
        super();
    }
    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public IdNotFoundException(String message) {
        super(message);
    }
    public IdNotFoundException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
