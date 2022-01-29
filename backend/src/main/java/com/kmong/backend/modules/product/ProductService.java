package com.kmong.backend.modules.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}