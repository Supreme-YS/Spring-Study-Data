package com.basic.feature_crud.model;

// 멤버 상태(회원, 탈퇴회원)
// @Enumerated 어노테이션에서 EnumType.String은 문자열 자체가 저장된다.
// 대출 가능, 대출 불가
public enum MemberStatus {
    ACTIVE, DEACTIVE
}
