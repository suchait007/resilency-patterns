package com.product.service.controller;

import com.product.service.async.ProductServiceFaster;
import com.product.service.dto.ProductDTO;
import com.product.service.dto.ProductDTOV1;
import com.product.service.service.ProductService;
import com.product.service.service.ProductServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/new")
@RequiredArgsConstructor
public class ProductControllerV1 {

    private final ProductServiceV1 productService;

    @GetMapping("/products")
    public ProductDTOV1 getProducts(@RequestParam("ids") List<String> productIds) {
        return productService.getProducts(productIds);
    }


}
