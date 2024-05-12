package com.rating.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Rating {

    private String productId;
    private List<RatingItem> ratingItemList;
}
