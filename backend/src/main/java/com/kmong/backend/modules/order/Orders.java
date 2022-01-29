package com.kmong.backend.modules.order;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>();

}
