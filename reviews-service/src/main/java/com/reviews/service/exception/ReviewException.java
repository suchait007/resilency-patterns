package com.reviews.service.exception;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ReviewException extends RuntimeException{

    public String errorDetail;
    public List<String> errorMessages;
    public Integer status;

    public ReviewException() {

    }

    public ReviewException(String errorDetail, List<String> errorMessages, Integer status) {
        super(errorDetail);
        this.errorDetail = errorDetail;
        this.errorMessages = new ArrayList<>(errorMessages);
        this.status = status;
    }
}
