package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}