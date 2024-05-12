package com.rating.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RatingServiceExceptionHandler {

    @ExceptionHandler(RatingException.class)
    public ResponseEntity<Object> handleException(RatingException ex) {

        RatingError ratingError = new RatingError();
        ratingError.setErrorDetail(ex.getErrorDetail());
        ratingError.setErrorMessage(ex.getErrorMessages());
        ratingError.setStatusCode(ex.getStatus());

        return new ResponseEntity<>(ratingError, HttpStatus.valueOf(ex.getStatus()));
    }
}
