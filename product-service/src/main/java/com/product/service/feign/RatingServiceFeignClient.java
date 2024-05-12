package com.product.service.feign;


import com.product.service.dto.RatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${rating.service.url}", value = "rating-service-feign" , path = "/v1")
public interface RatingServiceFeignClient {

    @GetMapping("/ratings")
    RatingDTO getRatings(@RequestParam("ids") List<String> productIds);
}
