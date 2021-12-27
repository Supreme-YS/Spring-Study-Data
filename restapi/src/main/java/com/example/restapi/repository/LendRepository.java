package com.example.restapi.repository;

import com.example.restapi.entity.Book;
import com.example.restapi.entity.Lend;
import com.example.restapi.entity.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {

    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
