package com.reviews.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewDTO {

    private List<Review> reviews;
}
