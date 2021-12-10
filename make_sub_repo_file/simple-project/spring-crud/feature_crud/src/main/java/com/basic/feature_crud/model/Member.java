package com.basic.feature_crud.model;

// 회원정보

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    // 회원 상태를 알려주는 @Enumerated
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    // 한 명의 회원이 도서 여러권을 대출할 수 있다. 회원과 대출은 1:N 관계
    @JsonBackReference
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lend> lends;
}
