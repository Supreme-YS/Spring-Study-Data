package com.basic.feature_crud.model;

// 대출

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

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

}
