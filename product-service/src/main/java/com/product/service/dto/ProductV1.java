package com.product.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductV1 {

    private String productId;
    private String name;
    private List<Rating> ratings;
}
