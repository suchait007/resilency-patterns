package com.rating.service.service;


import com.rating.service.dto.Rating;
import com.rating.service.dto.RatingDTO;
import com.rating.service.dto.RatingItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private static Map<String, Rating> ratingDTOMap = new HashMap<>();

    static {
        ratingDTOMap.put("1",new Rating("1", List.of(new RatingItem("12",2))));
        ratingDTOMap.put("2",new Rating("2", List.of(new RatingItem("13",1))));
        ratingDTOMap.put("3",new Rating("3", List.of(new RatingItem("14",2))));
        ratingDTOMap.put("4",new Rating("4", List.of(new RatingItem("15",3))));
        ratingDTOMap.put("5",new Rating("5", List.of(new RatingItem("16",4))));
        ratingDTOMap.put("6",new Rating("6", List.of(new RatingItem("17",4))));
        ratingDTOMap.put("7",new Rating("7", List.of(new RatingItem("18",5))));
        ratingDTOMap.put("8",new Rating("8", List.of(new RatingItem("19",1))));
        ratingDTOMap.put("9",new Rating("9", List.of(new RatingItem("20",2))));


    }

    public RatingDTO getRatings(List<String> productIds) {

        List<Rating> ratings =
        productIds.stream()
                .filter(productId -> ratingDTOMap.containsKey(productId))
                .map(id -> ratingDTOMap.get(id))
                .collect(Collectors.toList());


        return new RatingDTO(ratings);

    }
}
