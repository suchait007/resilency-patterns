package com.product.service.service;


import com.product.service.dto.Product;
import com.product.service.dto.ProductDTO;
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
public class ProductService {

    private final RatingService ratingService;
    private final ReviewService reviewService;

    public ProductDTO getProducts(List<String> productIds) {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        RatingDTO ratingDTOS = ratingService.getRatings(productIds);

        Map<String, List<Rating>> ratingsMap = getRatingsMap(ratingDTOS);

        ReviewDTO reviewDTOS = reviewService.getReviews(productIds);

        Map<String, List<Review>> reviewsMap = getReviewsMap(reviewDTOS);

        List<Product> products = prepareProductResponse(ratingsMap, reviewsMap, productIds);

        stopWatch.stop();
        log.info("Total time taken : {} Seconds.", stopWatch.getTotalTimeSeconds());

        return new ProductDTO(products);
    }

    public Map<String, List<Rating>> getRatingsMap(RatingDTO ratingDTO) {

        return ratingDTO.getRatings()
                .stream().collect(Collectors.groupingBy(Rating::getProductId));
    }

    public Map<String, List<Review>> getReviewsMap(ReviewDTO reviewDTO) {

        return reviewDTO.getReviews()
                .stream().collect(Collectors.groupingBy(Review::getProductId));
    }

    public List<Product> prepareProductResponse(Map<String, List<Rating>> ratingsMap, Map<String, List<Review>> reviewsMap,
                                       List<String> productIds) {

        List<Product> products = new ArrayList<>();

        productIds.stream()
                .forEach(productId -> {

                    List<Rating> ratings = null;
                    List<Review> reviews = null;

                    if(ratingsMap.containsKey(productId)) {
                        ratings = ratingsMap.get(productId);
                    }

                    if(reviewsMap.containsKey(productId)) {
                        reviews = reviewsMap.get(productId);
                    }

                    Product product = new Product();
                    product.setProductId(productId);
                    product.setRatings(ratings);
                    product.setReviews(reviews);

                    products.add(product);

                });

        return products;

    }
}
