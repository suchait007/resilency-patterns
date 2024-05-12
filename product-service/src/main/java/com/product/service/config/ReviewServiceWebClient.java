package com.product.service.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;


@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class ReviewServiceWebClient {

    @Value("${review.service.url}")
    private String reviewServiceUrl;

    @Bean
    @Qualifier("getReviewServiceWebclient")
    public WebClient getReviewServiceWebclient() {

        ConnectionProvider connectionProvider = ConnectionProvider.builder("reviewServiceConnectionPool")
                .maxConnections(200)
                .pendingAcquireMaxCount(100)
                .build();

        HttpClient client = HttpClient.create(connectionProvider)
                .responseTimeout(Duration.ofSeconds(5));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(reviewServiceUrl).build();
    }
}
