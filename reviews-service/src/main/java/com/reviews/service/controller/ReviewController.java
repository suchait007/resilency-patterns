package com.reviews.service.controller;

import com.reviews.service.dto.ReviewDTO;
import com.reviews.service.exception.ReviewException;
import com.reviews.service.service.ReviewService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    @RateLimiter(name = "rateLimitingAPI", fallbackMethod = "fallbackRateLimit")
    public ReviewDTO getReviews(@RequestParam("ids") List<String> productIds) throws InterruptedException {

        log.info("Received request with productIds : {} ", productIds);

        if(productIds.size() == 0) {
            throw new ReviewException("Review Exception",
                    List.of("No ids passed"), HttpStatus.BAD_REQUEST.value());
        }

        return reviewService.getRatings(productIds);
    }

    public ReviewDTO fallbackRateLimit(List<String> productIds, RequestNotPermitted ex) {

        throw ex;
    }
}
