package com.example.bunjang.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message<T> {
    @Builder
    public Message(T result) {
        this.code = HttpStatus.OK.value();
        this.message = "SUCCESS";
        this.result = result;
    }

    private Integer code;
    private String message;
    private T result;
}