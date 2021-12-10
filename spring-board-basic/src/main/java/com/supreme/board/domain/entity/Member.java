package com.supreme.board.domain.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String name;

    @Column(name = "RegDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Member(@NotNull String email,@NotNull String password, String name, LocalDate localDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.date = localDate.now();
    }
}
