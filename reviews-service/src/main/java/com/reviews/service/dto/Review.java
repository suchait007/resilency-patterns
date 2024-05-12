package com.reviews.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Review {

    private String productId;
    private List<ReviewItem> reviewItemList;
}
