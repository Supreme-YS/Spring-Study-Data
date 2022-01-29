package com.kmong.backend.modules.product;

import com.kmong.backend.modules.order.Order;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotNull
    private String productName;

    @NotNull
    private String productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
