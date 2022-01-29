package com.kmong.backend.modules.product;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.order.Orders;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String productName;

    @NotNull
    private String productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Builder
    public Product(Long id, String productName, String productPrice, Account account) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.account = account;
    }

    public void update(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
    }
}
