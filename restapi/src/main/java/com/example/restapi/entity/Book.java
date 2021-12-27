package com.example.restapi.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String isbn;

    // 관계 매핑
    @ManyToOne
    @JsonManagedReference // 순환참조 방지
    @JoinColumn(name = "author_id")
    private Author author;

    // 하나의 책은 여러번 대출이 가능하다.

    @JsonBackReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lend> lends;
}
