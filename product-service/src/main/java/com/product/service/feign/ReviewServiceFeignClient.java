package com.product.service.feign;


import com.product.service.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${review.service.url}", value = "review-service-feign" , path = "/v1")
public interface ReviewServiceFeignClient {

    @GetMapping("/reviews")
    ReviewDTO getReviews(@RequestParam("ids") List<String> productIds);
}
