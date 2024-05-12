package com.product.service.async;

import com.product.service.dto.RatingDTO;
import com.product.service.exception.ProductServiceRestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RatingServiceFaster {

    private final WebClient ratingServiceWebClient;

    public RatingServiceFaster(@Qualifier("getRatingServiceWebclient") WebClient ratingServiceWebClient) {
        this.ratingServiceWebClient = ratingServiceWebClient;
    }

    public CompletableFuture<RatingDTO> getRatings(List<String> productIds) {

        return CompletableFuture.supplyAsync(() -> ratingServiceWebClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/v1/ratings")
                                .queryParam("ids",productIds)
                                .build())
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new ProductServiceRestException("Exception while calling rating service."))
                )
                .bodyToFlux(RatingDTO.class)
                .onErrorResume( throwable -> {
                    log.error("Error : {} ", throwable.getMessage());
                    return Mono.empty();
                })
                .blockFirst());
    }
}
