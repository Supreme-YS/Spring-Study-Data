package com.kmong.backend.modules.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum AccountRole {

    ROLE_USER("사용자"),
    ROLE_ADMIN("관리자");

    private final String description;

    AccountRole(String description) {
        this.description = description;
    }
}
