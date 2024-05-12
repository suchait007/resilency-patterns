package com.rating.service.exception;


import lombok.Data;

import java.util.List;

@Data
public class RatingError {

    private Integer statusCode;
    private String errorDetail;
    private List<String> errorMessage;
}
