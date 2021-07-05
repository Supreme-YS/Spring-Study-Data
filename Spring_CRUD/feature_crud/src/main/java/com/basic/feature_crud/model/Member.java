package com.basic.feature_crud.model;

// 회원정보

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    // 회원 상태를 알려주는 Enumerated
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
}
