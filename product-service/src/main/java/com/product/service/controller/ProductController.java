package com.product.service.controller;


import com.product.service.dto.ProductDTO;
import com.product.service.service.ProductService;
import com.product.service.async.ProductServiceFaster;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductServiceFaster productServiceFaster;

    /*
    @GetMapping("/products")
    public ProductDTO getProducts(@RequestParam("ids") List<String> productIds) {
        return productService.getProducts(productIds);
    }

    @GetMapping("/async/products")
    public ProductDTO getProductsAsync(@RequestParam("ids") List<String> productIds) throws ExecutionException, InterruptedException {
        return productServiceFaster.getProducts(productIds);
    }
     */

    @GetMapping("/products")
    public ProductDTO getProducts() {

        List<String> productIds = List.of("1","6","7","3","4","5");

        return productService.getProducts(productIds);
    }

    @GetMapping("/async/products")
    public ProductDTO getProductsAsync() throws ExecutionException, InterruptedException {

        List<String> productIds1 = List.of("1","6","7","3","4","5");

        return productServiceFaster.getProducts(productIds1);
    }
}
