package com.kmong.backend.modules.product.dto;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.product.Product;
import lombok.Data;

@Data
public class ProductReq {
    String productName;
    String productPrice;

    public Product toProduct(Account account) {
        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        return product;
    }
}
