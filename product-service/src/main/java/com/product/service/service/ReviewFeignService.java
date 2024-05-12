package com.product.service.service;


import com.product.service.dto.ReviewDTO;
import com.product.service.feign.ReviewServiceFeignClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewFeignService {

    private final ReviewServiceFeignClient reviewServiceFeignClient;
    private static int COUNTER = 1;

    @Bulkhead(name = "reviewServiceBulkhead")
    @Retry(name = "reviewServiceRetry")
    public ReviewDTO getReviews(List<String> productIds) {
        log.info("Calling review-service with productIds : {} and count : {} ", productIds, COUNTER++);
        return reviewServiceFeignClient.getReviews(productIds);
    }
}
