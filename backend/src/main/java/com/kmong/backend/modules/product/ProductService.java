package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.account.AccountRepository;
import com.kmong.backend.modules.product.dto.ProductReq;
import com.kmong.backend.modules.product.dto.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    public ProductRes addProduct(Account account, ProductReq productReq) {
        Account accountByEmail = accountRepository.findByEmail(account.getEmail());
        Product newProduct = productReq.toProduct(account);
        productRepository.save(newProduct);
        return ProductRes.toProductRes(newProduct);
    }
}