package com.kmong.backend.modules.product.dto;

import com.kmong.backend.modules.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRes {
    Long productId;
    String productName;
    String productPrice;

    public static ProductRes toProductRes(Product product) {
        return ProductRes.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .build();
    }
}
