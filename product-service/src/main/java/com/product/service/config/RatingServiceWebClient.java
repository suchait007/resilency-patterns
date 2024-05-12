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
public class RatingServiceWebClient {

    @Value("${rating.service.url}")
    private String ratingServiceUrl;


    @Bean
    @Qualifier("getRatingServiceWebclient")
    public WebClient getRatingServiceWebclient() {

        ConnectionProvider connectionProvider = ConnectionProvider.builder("ratingServiceConnectionPool")
                .maxConnections(20)
                .pendingAcquireMaxCount(10)
                .build();

        HttpClient client = HttpClient.create(connectionProvider)
                .responseTimeout(Duration.ofSeconds(5));

        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl(ratingServiceUrl).build();
    }
}
