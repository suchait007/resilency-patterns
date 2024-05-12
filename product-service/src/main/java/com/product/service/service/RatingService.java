package com.product.service.service;

import com.product.service.dto.RatingDTO;
import com.product.service.feign.RatingServiceFeignClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingService {

    private final RatingServiceFeignClient ratingServiceFeignClient;


    @Bulkhead(name = "ratingServiceThreadPool")
    //@CircuitBreaker(name = "backendA" /*, fallbackMethod = "handleFallback"*/)
    public RatingDTO getRatings(List<String> productIds) {
        return ratingServiceFeignClient.getRatings(productIds);
    }

    /*
    public RatingDTO handleFallback(List<String> productIds, Throwable throwable) {
        log.info("Inside fallback method.");
        var rating = new RatingDTO();
        rating.setRatings(Collections.emptyList());

        return rating;
    }
     */

}
