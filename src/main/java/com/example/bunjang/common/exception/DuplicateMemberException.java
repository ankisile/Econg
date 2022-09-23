package com.example.bunjang.common.exception;

import org.springframework.http.HttpStatus;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException() {
        super();
    }
    public DuplicateMemberException(String message, Throwable cause) {
        super(message, cause);
    }
    public DuplicateMemberException(String message) {
        super(message);
    }
    public DuplicateMemberException(Throwable cause) {
        super(cause);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}