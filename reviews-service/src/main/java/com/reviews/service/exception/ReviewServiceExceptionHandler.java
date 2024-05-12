package com.reviews.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReviewServiceExceptionHandler {

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<Object> handleException(ReviewException ex) {

        ReviewError reviewError = new ReviewError();
        reviewError.setErrorDetail(ex.getErrorDetail());
        reviewError.setErrorMessage(ex.getErrorMessages());
        reviewError.setStatusCode(ex.getStatus());

        return new ResponseEntity<>(reviewError, HttpStatus.valueOf(ex.getStatus()));
    }
}
