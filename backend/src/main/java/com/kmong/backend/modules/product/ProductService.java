package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.account.AccountRepository;
import com.kmong.backend.modules.account.AccountService;
import com.kmong.backend.modules.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import com.kmong.backend.modules.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public Product addProduct(Account account, Product product) {
        Optional<Account> accountByEmail = accountRepository.findByEmail(account.getEmail());
        Product newProduct = Product.builder()
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .account(accountByEmail.get())
                .build();
        return productRepository.save(newProduct);
    }

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllProductsByAccountId(Account account) {
        List<Product> products = productRepository.findAllByAccountId(account.getId());
        return products;
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }

    public void deleteProductById(Account account, Long id) {
        Product productById = productRepository.findById(id).orElseThrow(NotFoundException::new);
        if( !(productById.getAccount().getId().equals(accountService.findById(account.getId()).get().getId()))) {
            throw new UnAuthorizedException();
        }
        productRepository.deleteById(id);
    }

}