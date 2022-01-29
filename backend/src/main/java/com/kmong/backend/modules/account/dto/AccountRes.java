package com.kmong.backend.modules.account.dto;

import com.kmong.backend.modules.account.Account;
import com.kmong.backend.modules.account.AccountRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRes {
    Long accountId;
    String email;
    String password;

    public static AccountRes toAccountRes(Account account) {
        return AccountRes.builder()
                .email(account.getEmail())
                .password(account.getPassword())
                .build();
    }
}
