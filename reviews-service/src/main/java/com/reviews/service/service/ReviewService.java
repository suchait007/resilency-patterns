package com.reviews.service.service;

import com.reviews.service.dto.Review;
import com.reviews.service.dto.ReviewDTO;
import com.reviews.service.dto.ReviewItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private static Map<String, Review> reviewDTOMap = new HashMap<>();

    static {
        reviewDTOMap.put("1",new Review("1", List.of(new ReviewItem("12","review-1"))));
        reviewDTOMap.put("2",new Review("2", List.of(new ReviewItem("13","review-2"))));
        reviewDTOMap.put("3",new Review("3", List.of(new ReviewItem("14","review-3"))));
        reviewDTOMap.put("4",new Review("4", List.of(new ReviewItem("15","review-4"))));
        reviewDTOMap.put("5",new Review("5", List.of(new ReviewItem("16","review-5"))));
        reviewDTOMap.put("6",new Review("6", List.of(new ReviewItem("17","review-6"))));
        reviewDTOMap.put("7",new Review("7", List.of(new ReviewItem("18","review-7"))));
        reviewDTOMap.put("8",new Review("8", List.of(new ReviewItem("19","review-8"))));
        reviewDTOMap.put("9",new Review("9", List.of(new ReviewItem("20","review-9"))));


    }

    public ReviewDTO getRatings(List<String> productIds) {

        List<Review> ratings =
                productIds.stream()
                        .filter(productId -> reviewDTOMap.containsKey(productId))
                        .map(id -> reviewDTOMap.get(id))
                        .collect(Collectors.toList());


        return new ReviewDTO(ratings);

    }
}
