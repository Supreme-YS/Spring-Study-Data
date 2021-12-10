package com.basic.feature_crud.model;

// 책 정보

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String isbn;

    // 저자와 도서는 1:N 관계, 한 저자는 여러개의 책을 쓸 수 있기 때문이다.
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;

    // 책과 대출은 1:N 관계, 하나의 책을 여러명이 대여할 수 있다.
    @JsonBackReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lend> lends;
}
