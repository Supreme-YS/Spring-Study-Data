package com.basic.feature_crud.model;

// 대출 상태
// @Enumerated 어노테이션에서 EnumType.String은 문자열 자체가 저장된다.
// 대출 가능, 대출 불가
public enum LendStatus {
    AVAILABLE, BURROWED
}
