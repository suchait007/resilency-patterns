package com.product.service.service;


import com.product.service.dto.ReviewDTO;
import com.product.service.exception.ProductServiceRestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    private final WebClient reviewServiceWebClient;

    public ReviewService(@Qualifier("getReviewServiceWebclient") WebClient reviewServiceWebClient) {
        this.reviewServiceWebClient = reviewServiceWebClient;
    }

    public ReviewDTO getReviews(List<String> productIds) {

        return reviewServiceWebClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/v1/reviews")
                                .queryParam("ids",productIds)
                                .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new ProductServiceRestException("Exception while calling review service."))
                )
                .bodyToFlux(ReviewDTO.class)
                .onErrorResume( throwable -> {
                    log.error("Error : {} ", throwable.getMessage());
                    return Mono.empty();
                })
                .blockFirst();
    }
}
