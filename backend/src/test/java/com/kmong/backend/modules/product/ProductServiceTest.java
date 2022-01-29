//package com.kmong.backend.modules.product;
//
//import com.kmong.backend.modules.account.Account;
//import com.kmong.backend.modules.account.AccountRole;
//import com.kmong.backend.modules.account.AccountService;
//import com.kmong.backend.modules.account.dto.AccountRes;
//import com.kmong.backend.modules.product.dto.ProductReq;
//import com.kmong.backend.modules.product.dto.ProductRes;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProductServiceTest {
//
//    @Autowired
//    private AccountService accountService;
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    void addProduct() {
//        //given
//        Account account = new Account();
//        account.setId(1L);
//        account.setEmail("dudtjr1225@gmail.com");
//        account.setPassword("testpw");
//
//        AccountRes newAccount = accountService.signUp(account);
//
//        ProductReq productReq = new ProductReq();
//        productReq.setProductName("첫 번째 상품");
//        productReq.setProductPrice("10000");
//        //when
//        ProductRes addProduct = productService.addProduct(, productReq);
//        Optional<Product> newProductById = productRepository.findById(addProduct.getProductId());
//        //then
//        Assertions.assertThat(addProduct.getProductId()).isEqualTo(newProductById);
//
//    }
//}