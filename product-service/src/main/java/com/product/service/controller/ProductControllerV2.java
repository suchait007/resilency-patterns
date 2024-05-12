package com.product.service.controller;

import com.product.service.dto.ProductDTOV2;
import com.product.service.service.ProductServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/new")
@RequiredArgsConstructor
public class ProductControllerV2 {

    private final ProductServiceV2 productService;

    @GetMapping("/products")
    public ProductDTOV2 getProducts(@RequestParam("ids") List<String> productIds) {
        return productService.getProducts(productIds);
    }


}
