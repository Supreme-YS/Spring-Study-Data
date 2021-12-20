package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    // 회원 상태를 위한 Enum
    @Enumerated(EnumType.STRING)
    private MemberStatus status = MemberStatus.ACTIVE;
    // private MemberGrade grade = MemberGrade.NORMAL;
    // private MemberGrade grade = MemberGrade.AUTH;
    // private MemberGrade grade = MemberGrade.CONTRIBUTOR;

    // 한 명의 회원이 여러 권을 대출할 수 있다.
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Lend> lends;

}
