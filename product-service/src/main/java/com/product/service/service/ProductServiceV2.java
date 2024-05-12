package com.product.service.service;


import com.product.service.dto.Product;
import com.product.service.dto.ProductDTO;
import com.product.service.dto.ProductDTOV2;
import com.product.service.dto.ProductV2;
import com.product.service.dto.Rating;
import com.product.service.dto.RatingDTO;
import com.product.service.dto.Review;
import com.product.service.dto.ReviewDTO;
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
public class ProductServiceV2 {

    private final ReviewFeignService reviewService;

    public ProductDTOV2 getProducts(List<String> productIds) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        ReviewDTO reviewDTOS = reviewService.getReviews(productIds);

        Map<String, List<Review>> reviewsMap = getReviewsMap(reviewDTOS);

        List<ProductV2> products = prepareProductResponse(reviewsMap, productIds);

        stopWatch.stop();
        log.info("Total time taken : {} Seconds.", stopWatch.getTotalTimeSeconds());

        return new ProductDTOV2(products);
    }


    public Map<String, List<Review>> getReviewsMap(ReviewDTO reviewDTO) {

        return reviewDTO.getReviews()
                .stream().collect(Collectors.groupingBy(Review::getProductId));
    }

    public List<ProductV2> prepareProductResponse(Map<String, List<Review>> reviewsMap,
                                       List<String> productIds) {

        List<ProductV2> products = new ArrayList<>();

        productIds.stream()
                .forEach(productId -> {

                    List<Review> reviews = null;


                    if(reviewsMap.containsKey(productId)) {
                        reviews = reviewsMap.get(productId);
                    }

                    ProductV2 product = new ProductV2();
                    product.setProductId(productId);
                    product.setReviews(reviews);

                    products.add(product);

                });

        return products;

    }
}
