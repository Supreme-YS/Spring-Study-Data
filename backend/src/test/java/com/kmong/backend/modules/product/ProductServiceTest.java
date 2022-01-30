package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.account.AccountService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
        Account newAccount = accountService.signUp(account);

        //when
        Product product = new Product();
        product.setId(1L);
        product.setProductName("첫 번째 상품");
        product.setProductPrice("1000");
        product.setAccount(account);

        //then
        Product newProduct = productService.addProduct(newAccount, product);
        Product productById = productService.findProductById(newProduct.getId());
        Assertions.assertThat(newProduct.getProductName()).isEqualTo(productById.getProductName());
    }

    @Test
    void deleteProductById() {
        //given
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        Account newAccount = accountService.signUp(account);

        //when
        Product product = new Product();
        product.setId(1L);
        product.setProductName("첫 번째 상품");
        product.setProductPrice("1000");
        product.setAccount(account);
        Product newProduct = productService.addProduct(newAccount, product);

        //then
        productService.deleteProductById(newAccount, newProduct.getId());
        Optional<Product> id = productRepository.findById(1L);
        Assertions.assertThat(id).isEmpty();
    }

    @Test
    void allProducts() {
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        Account newAccount = accountService.signUp(account);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("첫 번째 상품");
        product1.setProductPrice("1000");
        product1.setAccount(account);
        Product newProduct1 = productService.addProduct(newAccount, product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("두 번째 상품");
        product2.setProductPrice("1000");
        product2.setAccount(account);
        Product newProduct2 = productService.addProduct(newAccount, product2);
        //when
        List<Product> products = productService.allProducts();
        //then
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void findAllProductsByAccountId() {
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        Account newAccount = accountService.signUp(account);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("첫 번째 상품");
        product1.setProductPrice("1000");
        product1.setAccount(account);
        Product newProduct1 = productService.addProduct(newAccount, product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("두 번째 상품");
        product2.setProductPrice("1000");
        product2.setAccount(account);
        Product newProduct2 = productService.addProduct(newAccount, product2);
        //when
        List<Product> products = productService.findAllProductsByAccountId(newAccount);
        //then
        Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    void findProductById() {
        //given
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        Account newAccount = accountService.signUp(account);

        Product product = new Product();
        product.setId(1L);
        product.setProductName("첫 번째 상품");
        product.setProductPrice("1000");
        product.setAccount(account);
        Product newProduct = productService.addProduct(newAccount, product);

        //when
        Product productById = productService.findProductById(1L);
        Optional<Product> findProduct = productRepository.findById(1L);
        //then
        Assertions.assertThat(productById.getId()).isEqualTo(findProduct.get().getId());
    }
}