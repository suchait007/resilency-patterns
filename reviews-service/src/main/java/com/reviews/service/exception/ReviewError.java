package com.reviews.service.exception;


import lombok.Data;

import java.util.List;

@Data
public class ReviewError {

    private Integer statusCode;
    private String errorDetail;
    private List<String> errorMessage;
}
