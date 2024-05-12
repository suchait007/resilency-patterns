package com.product.service.async;

import com.product.service.dto.Product;
import com.product.service.dto.ProductDTO;
import com.product.service.dto.Rating;
import com.product.service.dto.RatingDTO;
import com.product.service.dto.Review;
import com.product.service.dto.ReviewDTO;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceFaster {

    private final RatingServiceFaster ratingService;
    private final ReviewServiceFaster reviewService;

    public ProductDTO getProducts(List<String> productIds) throws ExecutionException, InterruptedException {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        CompletableFuture<RatingDTO> ratingDTOCompletableFuture = ratingService.getRatings(productIds);
        CompletableFuture<ReviewDTO> reviewDTOCompletableFuture = reviewService.getReviews(productIds);

        CompletableFuture<List<Product>> listCompletableFuture = ratingDTOCompletableFuture.thenApply(this::getRatingsMap)
                .thenCombine(reviewDTOCompletableFuture.thenApply(this::getReviewsMap),
                        (ratingsMap, reviewsMap) -> prepareProductResponse(ratingsMap, reviewsMap, productIds));

        stopWatch.stop();
        log.info("Total time taken : {} Seconds.", stopWatch.getTotalTimeSeconds());

        return new ProductDTO(listCompletableFuture.get());
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

        List<CompletableFuture<Product>> completableFutures =
        productIds.stream()
                .map(productId ->
                        CompletableFuture.supplyAsync(() -> getProductResponse(ratingsMap, reviewsMap, productId)))
                .collect(Collectors.toList());

        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    private Product getProductResponse(Map<String, List<Rating>> ratingsMap, Map<String, List<Review>> reviewsMap
            ,String productId) {

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

        return product;
    }
}
