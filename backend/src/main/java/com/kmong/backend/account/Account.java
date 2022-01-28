package com.kmong.backend.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Account {
    @Id
    private Long id;

    private String email;

    // 암호화가 필요하다.
    private String password;

}
