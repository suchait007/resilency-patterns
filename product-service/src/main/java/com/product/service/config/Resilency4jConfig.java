package com.product.service.config;

/*

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

//@Configuration
public class Resilency4jConfig {

    @Value("${resilency.circuitBreaker.failureRateThreshold}")
    private Integer failureRateThreshold;

    @Value("${resilency.circuitBreaker.waitDurationInOpenStateDuration}")
    private Integer waitDurationInOpenStateDuration;

    @Value("${resilency.circuitBreaker.permittedNumberOfCallsInHalfOpenState}")
    private Integer permittedNumberOfCallsInHalfOpenState;

    @Value("${resilency.circuitBreaker.slidingWindowSize}")
    private Integer slidingWindowSize;


    @Bean
    public CircuitBreakerConfig getCircuitBreakerConfig() {

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(waitDurationInOpenStateDuration))
                .permittedNumberOfCallsInHalfOpenState(permittedNumberOfCallsInHalfOpenState)
                .slidingWindowSize(slidingWindowSize)
                .recordExceptions(IOException.class, TimeoutException.class)
                .build();

        return circuitBreakerConfig;
    }

    @Bean
    public CircuitBreakerRegistry getCircuitBreakerRegistry(final CircuitBreakerConfig circuitBreakerConfig) {

        CircuitBreakerRegistry circuitBreakerRegistry =
                CircuitBreakerRegistry.of(circuitBreakerConfig);

        return circuitBreakerRegistry;
    }


    @Bean(name = "ratingServiceCircuitBreaker")
    public CircuitBreaker getCircuitBreaker(final CircuitBreakerRegistry circuitBreakerRegistry) {

        CircuitBreaker circuitBreaker = circuitBreakerRegistry
                .circuitBreaker("ratingService");
        return circuitBreaker;
    }


}


 */