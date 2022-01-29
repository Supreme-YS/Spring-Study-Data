package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.account.AccountService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class ProductServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void addProduct() {
        //given
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        accountService.signUp(account);

        //when
        Product product = new Product();
        product.setId(1L);
        product.setProductName("첫 번째 상품");
        product.setProductPrice("1000");
        product.setAccount(account);

        //then
        Product newProduct = productService.addProduct(product);
        Optional<Product> newProductById = productRepository.findById(newProduct.getId());
        Assertions.assertThat(newProduct.getProductName()).isEqualTo(newProductById.get().getProductName());
    }
}