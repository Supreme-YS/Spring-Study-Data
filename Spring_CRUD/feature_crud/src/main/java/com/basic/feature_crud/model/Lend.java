package com.basic.feature_crud.model;

// 대출

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

// 책과 대출은 1:N 관계, 하나의 책을 여러명이 대여할 수 있다.

@Entity
@Getter
@Setter
@Table(name = "Lend")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant startOn;
    private Instant dueOn;

    @Enumerated(EnumType.STRING)
    private LendStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book book;
}
