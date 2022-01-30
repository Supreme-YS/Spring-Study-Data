package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.allProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // 상품등록
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Account account, @RequestBody Product product) {
        Product newProduct = productService.addProduct(account, product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // 단일 품목 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> productById(@PathVariable("productId") Long id, @RequestBody Product product) {
        Product productById = productService.findProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(Account account, @PathVariable("id")Long id) {
        productService.deleteProductById(account, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{accountId}/products")
    public ResponseEntity<?> productsByAccount (Account account, @PathVariable("accountId") Long id) {
        List<Product> allProductsByAccountId = productService.findAllProductsByAccountId(account);
        return new ResponseEntity<>(allProductsByAccountId, HttpStatus.OK);
    }
}
