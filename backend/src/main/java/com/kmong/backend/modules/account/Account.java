package com.kmong.backend.modules.account;

import com.kmong.backend.modules.order.Orders;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

//    @Enumerated(EnumType.STRING)
//    private AccountRole accountRole;

    @OneToMany(mappedBy = "account")
    List<Orders> orders = new ArrayList<>();

//    public Account(Long id, String email, String password) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//    }

    //    public void addAccount(Account updateAccount) {
//        updateAccount.getProducts().add(this);
//        this.setAccount(updateAccount);
//    }
}
