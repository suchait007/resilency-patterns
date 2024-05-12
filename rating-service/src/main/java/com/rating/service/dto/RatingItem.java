package com.rating.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingItem {

    private String ratingId;
    private Integer rating;
}
