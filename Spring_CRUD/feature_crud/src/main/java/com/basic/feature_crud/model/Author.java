package com.basic.feature_crud.model;

// 저자정보

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

// 저자와 도서는 1:N 관계, 한 저자는 여러개의 책을 쓸 수 있기 때문이다.

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")




public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;


    @JsonBackReference
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;
}