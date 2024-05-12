package com.rating.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RatingException extends RuntimeException{

    public String errorDetail;
    public List<String> errorMessages;
    public Integer status;

    public RatingException() {

    }

    public RatingException(String errorDetail, List<String> errorMessages, Integer status) {
        super(errorDetail);
        this.errorDetail = errorDetail;
        this.errorMessages = new ArrayList<>(errorMessages);
        this.status = status;
    }
}
