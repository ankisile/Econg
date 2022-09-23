//package com.example.bunjang.exception;
//
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice(basePackages = "com.example.bunjang.controller")
//public class ExceptionControllerAdvice {
//
//    @ExceptionHandler(AbstractEbloBaseException.class)
//    protected ResponseEntity<ErrorResponse> handleApiException(AbstractEbloBaseException e) {
//        return ResponseEntity
//                .status(e.getHttpStatus())
//                .body(
//                        new ErrorResponse(
//                                e.getHttpStatus().value(),
//                                e.getHttpStatus().getReasonPhrase(),
//                                e.getDetailMessage()
//                        )
//                );
//    }
//
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<ErrorResponse> exception(Exception e) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(
//                        new ErrorResponse(
//                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//                                e.getLocalizedMessage()
//                        )
//                );
//    }
//}
