package com.rating.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RatingDTO {

    private List<Rating> ratings;
}
