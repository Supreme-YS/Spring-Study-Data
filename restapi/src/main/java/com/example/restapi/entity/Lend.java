package com.example.restapi.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "lend")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant startOn;
    private Instant dueOn;

    @Enumerated(EnumType.ORDINAL)
    private LendStatus status;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "member_id")
    private Member member;

}
