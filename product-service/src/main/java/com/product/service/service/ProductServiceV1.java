package com.product.service.service;


import com.product.service.dto.ProductDTOV1;
import com.product.service.dto.ProductV1;
import com.product.service.dto.Rating;
import com.product.service.dto.RatingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceV1 {

    private final RatingService ratingService;

    public ProductDTOV1 getProducts(List<String> productIds) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        RatingDTO ratingDTOS = ratingService.getRatings(productIds);

        Map<String, List<Rating>> ratingsMap = getRatingsMap(ratingDTOS);

        List<ProductV1> products = prepareProductResponse(ratingsMap, productIds);

        stopWatch.stop();
        log.info("Total time taken : {} Seconds.", stopWatch.getTotalTimeSeconds());

        return new ProductDTOV1(products);
    }

    public Map<String, List<Rating>> getRatingsMap(RatingDTO ratingDTO) {

        return ratingDTO.getRatings()
                .stream().collect(Collectors.groupingBy(Rating::getProductId));
    }

    public List<ProductV1> prepareProductResponse(Map<String, List<Rating>> ratingsMap, List<String> productIds) {

        List<ProductV1> products = new ArrayList<>();

        productIds.stream()
                .forEach(productId -> {

                    List<Rating> ratings = null;

                    if(ratingsMap.containsKey(productId)) {
                        ratings = ratingsMap.get(productId);
                    }

                    ProductV1 product = new ProductV1();
                    product.setProductId(productId);
                    product.setRatings(ratings);

                    products.add(product);

                });

        return products;

    }
}
