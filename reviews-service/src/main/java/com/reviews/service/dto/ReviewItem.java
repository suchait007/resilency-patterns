package com.reviews.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewItem {

    private String reviewId;
    private String review;
}
