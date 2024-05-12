package com.rating.service.controller;

import com.rating.service.dto.RatingDTO;
import com.rating.service.exception.RatingException;
import com.rating.service.service.RatingService;
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
public class RatingController {

    private final RatingService ratingService;

    @GetMapping("/ratings")
    public RatingDTO getRatings(@RequestParam("ids") List<String> productIds) throws InterruptedException {

        log.info("Received request for ids : {} ", productIds);


        Thread.sleep(2000);

        if(productIds.size() == 0) {
            throw new RatingException("Rating Exception",
                    List.of("No ids passed"), HttpStatus.BAD_REQUEST.value());
        }

        return ratingService.getRatings(productIds);
    }
}
